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
public class DressType {
	private int typeId;
	private String typeName;
	private String typeDescription;
	private String comment;
	private User user;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
