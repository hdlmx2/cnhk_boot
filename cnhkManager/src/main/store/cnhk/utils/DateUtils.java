package store.cnhk.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtils {
    public static Date strToDate(String strDate) {
        if (strDate != null && !"".equals(strDate)) {
            String str = strDate;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = null;
            try {
                d = format.parse(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Date date = new Date(d.getTime());
            return date;
        }
        return null;

    }
}
