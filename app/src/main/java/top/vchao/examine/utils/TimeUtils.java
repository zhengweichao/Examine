package top.vchao.examine.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ 创建时间: 2017/5/25 on 17:14.
 * @ 描述：时间工具类
 * @ 作者: 郑卫超 QQ: 2318723605
 */

public class TimeUtils {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimeUtils() {
        throw new AssertionError();
    }


    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getNowTime() {
        return getTime(getCurrentTimeInLong(), DEFAULT_DATE_FORMAT);
    }

    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }


    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }


    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }


}

