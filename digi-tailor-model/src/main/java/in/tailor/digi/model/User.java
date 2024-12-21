package in.tailor.digi.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Admin
 * @version 1.0.0
 * @since 1.0.0
 *
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class User {
	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNo;
	private String alternateMobileNo;
	private String email;
	private String userName;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
