/**
 * 
 */
package in.tailor.digi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Admin
 *
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DtsApiResponse<T> {
	/**
	 * 
	 */
	private int httpStatus;
	private String message;
	private T result;
}
