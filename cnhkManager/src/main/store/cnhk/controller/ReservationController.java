package store.cnhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import store.cnhk.pojo.Reservation;
import store.cnhk.pojo.ServiceTimeSection;
import store.cnhk.service.ReservationService;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class ReservationController {
    @Autowired
    public ReservationService reservationService;

    @RequestMapping("/reservation")
    @ResponseBody
    public Map<String, Object> list(@RequestParam("userName") String userName, @RequestParam("phoneNumber") String phoneNumber,
                                    @RequestParam("reservationDate") String reservationDateString) {
        List<Reservation> list = reservationService.list(userName, phoneNumber, reservationDateString);
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> reservations = new LinkedList<>();
        int i = 1;
        for (Reservation reservation : list) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("rid", reservation.getId());
            temp.put("id", i);
            temp.put("userName", reservation.getUserName());
            temp.put("phoneNumber", reservation.getPhoneNumber());
            temp.put("reservationDate", reservation.getReservationDate().toString());
            temp.put("serviceTimeSection", reservation.getServiceTimeSection().getServiceTimeSection());
            temp.put("serviceId", reservation.getCnhkproduct().getServiceName());
            temp.put("isArrivalsStore", reservation.getIsArrivalsStore());
            temp.put("optionTime", reservation.getOptionTime().toString());
            reservations.add(temp);
            i++;
        }
        result.put("code", "0");
        result.put("msg", "");
        result.put("count", list.size());
        result.put("data", reservations);
        return result;
    }

    @RequestMapping(value = "reservationAdd", method = {RequestMethod.POST})
    public void add(@RequestBody Reservation reservation) {
        ServiceTimeSection serviceTimeSection = new ServiceTimeSection();
        reservation.setServiceTimeSection(serviceTimeSection);
        reservationService.add(reservation);
    }

    @RequestMapping("reservationUpdate")
    public void update(@RequestBody Reservation reservation, @RequestParam String cnhkProduct, @RequestParam int serviceTimeSection) {
        reservationService.update(reservation);

    }

    @RequestMapping("reservationGet")
    @ResponseBody
    public Reservation getById(@RequestParam("id") int id) throws IOException {
        Reservation reservation = reservationService.getById(id);
        return reservation;

    }

    @RequestMapping("reservationDelete")
    public void delete(@RequestBody Reservation reservation) {
        reservationService.delete(reservation);
    }
}
