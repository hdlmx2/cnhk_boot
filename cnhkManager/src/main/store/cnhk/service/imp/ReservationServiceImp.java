package store.cnhk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.cnhk.dao.ReservationDao;
import store.cnhk.pojo.Reservation;
import store.cnhk.service.ReservationService;
import store.cnhk.utils.DateUtils;

import java.sql.Date;
import java.util.List;

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
    public void update(Reservation reservation) {
        reservationDao.update(reservation);
    }


    @Override
    public void delete(Reservation reservation) {
        reservationDao.delete(reservation);

    }

}
