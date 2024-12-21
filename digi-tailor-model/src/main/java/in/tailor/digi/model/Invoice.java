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
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Invoice {
	private int invoiceId;
	private LocalDateTime invoiceDate;
	private double billAmount;
	private double discountedAmount;
	private double advance;
	private double totalAmount;
	private double paidAmount;
	private Customer customer;

}
