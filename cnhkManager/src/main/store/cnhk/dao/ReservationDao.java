package store.cnhk.dao;

import store.cnhk.pojo.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationDao {
    List<Reservation> list(String userName, String phoneNumber, Date reservationDate);

    Reservation getById(Integer id);

    void add(Reservation reservation);

    void delete(Reservation reservation);

    void update(Reservation reservation);


}
