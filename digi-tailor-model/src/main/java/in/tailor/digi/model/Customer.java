package in.tailor.digi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Admin
 * @version 1.0.0
 * @since 1.0.0
 */
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class Customer {
    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNo;
    private String email;
    private User user;
    private Shop shop;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
