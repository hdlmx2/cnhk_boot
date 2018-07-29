package store.cnhk.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import store.cnhk.pojo.Cnhkproduct;
import store.cnhk.pojo.Reservation;
import store.cnhk.pojo.ServiceTimeSection;
import store.cnhk.service.ReservationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller

public class ReservationController {
    @Autowired
    public ReservationService reservationService;

    @RequestMapping("/reservation")
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String phoneNumber = request.getParameter("phoneNumber");
        String reservationDateString = request.getParameter("reservationDate");
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
        response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        PrintWriter out = null;
        out = response.getWriter();
        out.print(gson.toJson(result));
        out.flush();
        out.close();
    }

    @RequestMapping("reservationAdd")
    public void add(HttpServletRequest request) {
        ServiceTimeSection serviceTimeSection = new ServiceTimeSection();
        Cnhkproduct cnhkproduct = new Cnhkproduct();
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        serviceTimeSection.setId(2);
        cnhkproduct.setId("001");
        Reservation reservation = new Reservation();
        reservation.setId(1002);
        reservation.setUserName("穆昕");
        reservation.setPhoneNumber("18851401673");
        reservation.setServiceTimeSection(serviceTimeSection);
        reservation.setCnhkproduct(cnhkproduct);
        reservation.setReservationDate(date1);
        reservationService.add(reservation);
    }

    @RequestMapping("reservationUpdate")
    public void update(@RequestBody Reservation reservation) {
        reservationService.update(reservation);

    }

    @RequestMapping("reservationGet")
    public void getById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id").toString());
        Reservation reservation = reservationService.getById(id);
        response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        PrintWriter out = null;
        out = response.getWriter();
        out.print(gson.toJson(reservation));
        out.flush();
        out.close();
    }

    @RequestMapping("reservationDelete")
    public void delete(@RequestBody Reservation reservation) {
        reservationService.delete(reservation);
    }
}
