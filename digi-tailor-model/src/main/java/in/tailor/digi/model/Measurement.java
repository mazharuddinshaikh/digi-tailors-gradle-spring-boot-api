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
public class Measurement {
	private int measurementId;
	private Dress dress;
	private String measurementImage;
	private String rawDressImage;
	private String patternImage;
	private String seavedImage;
	private String comment;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
