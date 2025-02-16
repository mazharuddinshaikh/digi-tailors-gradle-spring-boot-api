/**
 *
 */
package in.tailor.digi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 */
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class Shop {
    private String shopId;
    private String shopName;
    private String shopCode;
    private Address shopAddress;
    private User shopOwner;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private String holiday;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime openTime;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime closeTime;
    private String shopStatus;
    private String shopImage;
    private String mobileNo;
    private String alternateMobileNo;
}
