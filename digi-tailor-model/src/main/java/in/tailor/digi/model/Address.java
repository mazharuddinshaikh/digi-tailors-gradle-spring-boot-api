package in.tailor.digi.model;

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
public class Address {
	private String addressId;
	private String city;
	private String state;
	private String zipCode;
	private String addressLineOne;
	private String addressLineTwo;
//	user who added the address
	private String updatedBy;

}
