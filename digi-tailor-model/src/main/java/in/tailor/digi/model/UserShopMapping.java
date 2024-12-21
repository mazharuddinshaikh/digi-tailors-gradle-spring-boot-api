package in.tailor.digi.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserShopMapping {
	private String userId;
	private String shopId;
	private String userType;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
