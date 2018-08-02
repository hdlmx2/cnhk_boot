package store.cnhk.dao.imp;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import store.cnhk.dao.ReservationDao;
import store.cnhk.pojo.Reservation;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
@Component
public class ReservationDaoImp implements ReservationDao {
    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<Reservation> list(String userName, String phoneNumber, Date reservationDate) {

        StringBuffer hql = new StringBuffer("from Reservation r where 1=1");
        if (!StringUtils.isEmpty(userName)) {
            hql.append(" and r.userName like: userName");
        }
        if (!StringUtils.isEmpty(phoneNumber)) {
            hql.append(" and  r.phoneNumber like :phoneNumber");
        }
        if (reservationDate != null) {
            hql.append(" and r.reservationDate=:reservationDate");
        }
        hql.append(" order by r.reservationDate desc,r.operateTime desc");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql.toString());

        if (!StringUtils.isEmpty(userName)) {
            query.setParameter("userName", "%" + userName + "%");
        }
        if (!StringUtils.isEmpty(phoneNumber)) {
            query.setParameter("phoneNumber", "%" + phoneNumber + "%");
        }
        if (reservationDate != null) {
            query.setParameter("reservationDate", reservationDate);
        }
        List<Reservation> result = query.list();

        return result;
    }

    @Override
    public Reservation getById(Integer id) {
        return sessionFactory.getCurrentSession().get(Reservation.class, id);
    }


    @Override
    public void add(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        session.save(reservation);
        session.flush();


    }

    @Override
    public void delete(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(reservation);
        session.flush();


    }

    @Override
    public void update(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        session.update(reservation);
        session.flush();

    }

    @Override
    public List<Map<String,Object>> reservationServiceTimeSectionCount(Date reservationDate) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select new Map(r.serviceTimeSection as serviceTimeSection, count(r.id) as count) from Reservation r,ServiceTimeSection s where r.serviceTimeSection=s.id and  r.reservationDate=:reservationDate group by r.serviceTimeSection order by r.serviceTimeSection";
        Query query = session.createQuery(hql);
        query.setParameter("reservationDate", reservationDate);
        List<Map<String,Object>> reservationCount = query.list();
        return reservationCount;

    }

}
