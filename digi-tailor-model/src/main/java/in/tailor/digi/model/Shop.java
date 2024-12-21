/**
 * 
 */
package in.tailor.digi.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Shop {
	private String shopId;
	private String shopName;
	private String shopCode;
	private Address shopAddress;
	private User shopOwner;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
