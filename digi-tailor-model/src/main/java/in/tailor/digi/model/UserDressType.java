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
public class UserDressType {
	private int id;
	private DressType dressType;
	private User user;
	private double price;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
