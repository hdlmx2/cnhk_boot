package store.cnhk.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import store.cnhk.pojo.CnhkProduct;
import store.cnhk.pojo.Reservation;
import store.cnhk.pojo.ServiceTimeSection;
import store.cnhk.service.ReservationService;
import store.cnhk.utils.DateUtils;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class ReservationController {
    private static final Logger logger = LogManager.getLogger(ReservationController.class.getName());

    @Autowired
    public ReservationService reservationService;

    @RequestMapping("/reservation")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "phoneNumber", required = false
    ) String phoneNumber, @RequestParam(value = "reservationDate", required = false) String reservationDateString) {
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
            temp.put("serviceTimeSectionId", reservation.getServiceTimeSection().getId());
            temp.put("cnhkProduct", reservation.getCnhkproduct().getServiceName());
            temp.put("cnhkProductId", reservation.getCnhkproduct().getId());
            temp.put("isArrivalsStore", reservation.getIsArrivalsStore());
            temp.put("operateTime", reservation.getOperateTime().toString());
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
    @ResponseBody
    public Map<String, Boolean> add(@RequestParam("userName") String userName,
                                    @RequestParam("phoneNumber") String phoneNumber,
                                    @RequestParam("cnhkProductId") String cnhkProductId,
                                    @RequestParam("serviceTimeSectionId") String serviceTimeSectionId,
                                    @RequestParam("reservationDate") String reservationDateString) {
        Reservation reservation = new Reservation();
        ServiceTimeSection serviceTimeSection = new ServiceTimeSection();
        serviceTimeSection.setId(Integer.parseInt(serviceTimeSectionId));
        CnhkProduct cnhkProduct = new CnhkProduct();
        cnhkProduct.setId(cnhkProductId);
        reservation.setUserName(userName);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setServiceTimeSection(serviceTimeSection);
        reservation.setCnhkproduct(cnhkProduct);
        Date reservationDate = DateUtils.strToDate(reservationDateString);
        reservation.setReservationDate(reservationDate);
        boolean ifSuccess = true;
        try {
            reservationService.add(reservation);
        } catch (Exception e) {
            logger.error(e);
            ifSuccess = false;
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put("ifSuccess", ifSuccess);
        return result;

    }

    @RequestMapping("reservationUpdate")
    @ResponseBody
    public Map<String, Boolean> update(int id, String userName,
                                       String phoneNumber,
                                       Date reservationDate,
                                       String cnhkProductId,
                                       int serviceTimeSectionId) {
        Reservation reservation = new Reservation();
        ServiceTimeSection serviceTimeSection = new ServiceTimeSection();
        serviceTimeSection.setId(serviceTimeSectionId);
        CnhkProduct cnhkProduct = new CnhkProduct();
        cnhkProduct.setId(cnhkProductId);
        reservation.setId(id);
        reservation.setUserName(userName);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setServiceTimeSection(serviceTimeSection);
        reservation.setCnhkproduct(cnhkProduct);
        reservation.setReservationDate(reservationDate);
        reservationService.update(reservation);
        boolean ifSuccess = true;
        try {
            reservationService.add(reservation);
        } catch (Exception e) {
            logger.error(e);
            ifSuccess = false;
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put("ifSuccess", ifSuccess);
        return result;
    }

    @RequestMapping("reservationGet")
    @ResponseBody
    public Reservation getById(@RequestParam("id") int id) {
        Reservation reservation = reservationService.getById(id);
        return reservation;

    }

    @RequestMapping("reservationDelete")
    @ResponseBody
    public Map<String, Boolean> delete(@RequestBody Reservation reservation) {
        reservationService.delete(reservation);
        boolean ifSuccess = true;
        try {
            reservationService.add(reservation);
        } catch (Exception e) {
            logger.error(e);
            ifSuccess = false;
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put("ifSuccess", ifSuccess);
        return result;
    }

    @ResponseBody
    @RequestMapping("serviceTimeSectionCount")
    public List<Map<String, Object>> reservationServiceTimeSectionCount(Date reservationDate) {
        return reservationService.reservationServiceTimeSectionCount(reservationDate);
    }
}
