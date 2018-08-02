package store.cnhk.service;

import store.cnhk.pojo.Reservation;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ReservationService {
    List<Reservation> list(String userName, String phoneNumber, String reservationDate);
    Reservation getById(Integer id);
    void add(Reservation reservation);
    void update(Reservation reservation);
    void delete(Reservation reservation);
    List<Map<String,Object>>reservationServiceTimeSectionCount(Date reservationDate);

}
