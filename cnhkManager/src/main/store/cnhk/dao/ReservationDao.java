package store.cnhk.dao;

import store.cnhk.bean.Page;
import store.cnhk.pojo.Reservation;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ReservationDao {
    List<Reservation> list(String userName, String phoneNumber, Date reservationDate,Page page);
    List<Map<String,Object>> totalPage(String userName, String phoneNumber, Date reservationDate);

    Reservation getById(Integer id);

    void add(Reservation reservation);

    void delete(Reservation reservation);

    void update(Reservation reservation);

    List<Map<String, Object>> reservationServiceTimeSectionCount(Date ReservationDate);


}
