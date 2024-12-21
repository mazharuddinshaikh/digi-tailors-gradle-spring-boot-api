/**
 * 
 */
package in.tailor.digi.model;

/**
 * @author Admin
 *
 */

public class DtsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final transient DtsApiResponse<String> response;

	public DtsException(DtsApiResponse<String> response) {
		super();
		this.response = response;
	}

	public DtsApiResponse<String> getResponse() {
		return response;
	}

}
