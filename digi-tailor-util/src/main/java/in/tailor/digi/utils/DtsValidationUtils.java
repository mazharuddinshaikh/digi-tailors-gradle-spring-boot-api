/**
 * 
 */
package in.tailor.digi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public final class DtsValidationUtils {
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String MOBILE_NO_REGEX = "^[6-9][0-9]{9}$";

	private DtsValidationUtils() {
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
