package dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import store.cnhk.service.ReservationService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestResearvationDao {

    private ReservationService service;

    @Before
    public void init() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        service = (ReservationService) applicationContext.getBean("reservationServiceImp");
    }

    @Test
    public void test() {
        Date data = new Date();
        java.sql.Date date2 = new java.sql.Date(data.getTime());
        List<Map<String, Object>> result = service.reservationServiceTimeSectionCount(date2);
        System.out.println(result);
    }
}
