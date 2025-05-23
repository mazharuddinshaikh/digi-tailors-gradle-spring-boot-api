package in.tailor.digi.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DtsCodeUtils {

    private DtsCodeUtils() {

    }

    public static String generateDressId() {
        return "DTS_DRESS_" + generateDateTimeCode();
    }

    public static String generateCustomerId() {
        return "DTS_CUST_" + generateDateTimeCode();
    }

    public static String generateUserId() {
        return "DTS_USR_" + generateDateTimeCode();
    }

    public static String generateShopCode() {
        return "DTS_SHP_" + generateDateTimeCode();
    }

    public static String generateAddressId() {
        return "DTS_ADD_" + generateDateTimeCode();
    }

    public static String generateShopId() {
        return "DTS_SHP_" + generateDateTimeCode();
    }

    private static String generateDateTimeCode() {
        final LocalDateTime currentDateTime = DtsDateTimeUtil.getIndianCurrentDateTime();
        return currentDateTime.getYear() + getTensValue(currentDateTime.getMonthValue())
                + getTensValue(currentDateTime.getDayOfMonth()) + "_" + getTensValue(currentDateTime.getHour())
                + getTensValue(currentDateTime.getMinute()) + getTensValue(currentDateTime.getSecond());
    }

    private static String getTensValue(int value) {
        if (value < 10) {
            return "0" + value;
        }
        return String.valueOf(value);
    }

}
