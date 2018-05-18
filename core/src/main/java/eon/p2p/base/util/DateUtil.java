package eon.p2p.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {


    //日期格式化
    private static SimpleDateFormat s = new SimpleDateFormat();


    private static Calendar c = Calendar.getInstance();

    /**
     * 得到传入日期的当天24点日期
     *
     * @param date
     * @return
     */
    public static Date endDate(Date date) {
        if (date != null) {

            c.setTime(date);
            c.add(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.add(Calendar.SECOND, -1);
            return c.getTime();
        }
        return null;
    }

    /**
     * 得到传入日期的当天00点日期
     *
     * @param date
     * @return
     */
    public static Date startDate(Date date) {
        if (date != null) {
            c.setTime(date);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            return c.getTime();
        }
        return null;
    }

    /**
     * 计算两个日期之间的时间差
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long secondsBetween(Date time1, Date time2) {
        return Math.abs((time1.getTime() - time2.getTime()) / 1000l);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        s.applyPattern(pattern);
        return s.format(date);
    }

    public static Date addMonths(Date start, int month){
        c.setTime(start);
        c.add(Calendar.MONTH,month);
        return c.getTime();
    }
}
