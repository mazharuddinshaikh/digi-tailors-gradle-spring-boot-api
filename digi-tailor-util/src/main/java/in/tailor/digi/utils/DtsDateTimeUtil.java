package in.tailor.digi.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DtsDateTimeUtil {
    private static final String IST_ZONE = "Asia/Kolkata";

    private DtsDateTimeUtil() {
    }

    public static LocalDateTime convertTimetamptoLocalDateTime(Timestamp sqlTimeStamp) {
        if (sqlTimeStamp != null) {
            sqlTimeStamp.toLocalDateTime();
        }
        return null;
    }

    public static LocalDateTime getIndianCurrentDateTime() {
        return getCurrentDateTime(IST_ZONE);
    }

    private static LocalDateTime getCurrentDateTime(String zone) {
        return LocalDateTime.now(ZoneId.of(zone));
    }
}
