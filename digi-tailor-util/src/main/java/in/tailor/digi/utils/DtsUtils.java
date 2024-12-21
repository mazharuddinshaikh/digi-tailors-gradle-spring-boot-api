/**
 * 
 */
package in.tailor.digi.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public final class DtsUtils {
	private static final String IST_ZONE = "Asia/Kolkata";
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String MOBILE_NO_REGEX = "^[6-9][0-9]{9}$";
	private DtsUtils() {
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
		final LocalDateTime currentDateTime = getIndianCurrentDateTime();
		return currentDateTime.getYear() + getTensVlaue(currentDateTime.getMonthValue())
				+ getTensVlaue(currentDateTime.getDayOfMonth()) + "_" + getTensVlaue(currentDateTime.getHour())
				+ getTensVlaue(currentDateTime.getMinute()) + getTensVlaue(currentDateTime.getSecond());
	}

	private static String getTensVlaue(int value) {
		if (value < 10) {
			return "0" + value;
		}
		return String.valueOf(value);
	}

	public static boolean isValidEmail(String email) {
		Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
		Matcher emailMatcher = emailPattern.matcher(email);
		return emailMatcher.matches();
	}

	public static boolean isValidMobileNo(String mobileNo) {
		Pattern emailPattern = Pattern.compile(MOBILE_NO_REGEX);
		Matcher emailMatcher = emailPattern.matcher(mobileNo);
		return emailMatcher.matches();
	}
}
