/**
 *
 */
package in.tailor.digi.customer.controller;

import in.tailor.digi.customer.service.CustomerService;
import in.tailor.digi.model.Customer;
import in.tailor.digi.model.DtsApiResponse;
import in.tailor.digi.model.DtsException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */

@RestController
@RequestMapping("customer")
@Setter
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/test")
    @Operation(summary = "Test If Application is running")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("Customer Api working");
    }

    @GetMapping("/v1/user/{userId}/{offset}/{limit}")
    @Operation(summary = "Get customer by user")
    public ResponseEntity<List<Customer>> getCustomerByUser(@PathVariable("userId") String userId, @PathVariable("offset") Integer offset,
                                                            @PathVariable("limit") Integer limit) throws DtsException {
        var customerList = customerService.getCustomerByUser(userId, limit, offset);
        if (!CollectionUtils.isEmpty(customerList)) {
            return ResponseEntity.ok(customerList);
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("no customer found")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());

    }

    @GetMapping("/v1/shop/{shopId}/{offset}/{limit}")
    @Operation(summary = "Get customer by shop")
    public ResponseEntity<List<Customer>> getCustomerByShop(@PathVariable("shopId") String shopId, @PathVariable("offset") Integer offset,
                                                            @PathVariable("limit") Integer limit) throws DtsException {
        var customerList = customerService.getCustomerByShop(shopId, limit, offset);
        if (!CollectionUtils.isEmpty(customerList)) {
            return ResponseEntity.ok(customerList);
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("no customer found")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());
    }

    @PostMapping("/v1")
    @Operation(summary = "Add customer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) throws DtsException {
        customerService.addCustomer(customer).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build()));
        return ResponseEntity.ok("Customer added successfully");
    }

    @PutMapping("/v1")
    @Operation(summary = "Update customer")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) throws DtsException {
        customerService.updateCustomer(customer).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build()));
        return ResponseEntity.ok("Customer updated successfully");
    }
}
