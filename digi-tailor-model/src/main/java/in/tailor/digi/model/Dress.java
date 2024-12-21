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
public class Dress {
	private String dressId;
	private String comment;
	private User user;
	private Shop shop;
	private Customer customer;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
