package store.cnhk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.cnhk.dao.ReservationDao;
import store.cnhk.pojo.Reservation;
import store.cnhk.pojo.ServiceTimeSection;
import store.cnhk.service.ReservationService;
import store.cnhk.utils.DateUtils;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service

public class ReservationServiceImp implements ReservationService {
    @Autowired
    protected ReservationDao reservationDao;

    @Override
    @Transactional
    public List<Reservation> list(String userName, String phoneNumber, String reservationDateString) {
        Date reservationDate = DateUtils.strToDate(reservationDateString);
        return reservationDao.list(userName, phoneNumber, reservationDate);
    }

    @Override
    public Reservation getById(Integer id) {
        return reservationDao.getById(id);
    }

    @Override
    @Transactional
    public void add(Reservation reservation) {
        reservationDao.add(reservation);
    }

    @Override
    @Transactional
    public void update(Reservation reservation) {
        reservationDao.update(reservation);
    }


    @Override
    @Transactional
    public void delete(Reservation reservation) {
        reservationDao.delete(reservation);

    }

    @Transactional
    @Override
    public List<Map<String, Object>> reservationServiceTimeSectionCount(Date reservationDate) {
        List<Map<String, Object>> result = reservationDao.reservationServiceTimeSectionCount(reservationDate);
        List<Map<String, Object>> data = new LinkedList<>();
        if (result != null && result.size() > 0) {
            for (Map reservationCount : result) {
                ServiceTimeSection time = (ServiceTimeSection) reservationCount.get("serviceTimeSection");
                Long count = (Long) reservationCount.get("count");
                Map<String, Object> temp = new HashMap<>();
                temp.put("count", count);
                temp.put("time", time.getId());
                data.add(temp);
            }

        }
        return data;
    }
}
