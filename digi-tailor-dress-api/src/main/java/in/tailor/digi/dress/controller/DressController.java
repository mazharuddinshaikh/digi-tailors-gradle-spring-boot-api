/**
 *
 */
package in.tailor.digi.dress.controller;

import in.tailor.digi.dress.service.DressService;
import in.tailor.digi.model.Dress;
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
@RequestMapping("dress")
@Setter
public class DressController {
    @Autowired
    private DressService dressService;

    @GetMapping("/test")
    @Operation(summary = "Test If Application is running")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("Dress Api working");
    }

    @GetMapping("/v1/user/{userId}/{offset}/{limit}")
    @Operation(summary = "Get dress list by user")
    public ResponseEntity<List<Dress>> getDressByUser(@PathVariable("userId") String userId, @PathVariable("offset") Integer offset,
                                                      @PathVariable("limit") Integer limit) throws DtsException {
        final var dressList = dressService.getDressByUser(userId, limit, offset);
        if (!CollectionUtils.isEmpty(dressList)) {
            return ResponseEntity.ok(dressList);
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("no customer found")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());
    }

    @GetMapping("/v1/shop/{shopId}/{offset}/{limit}")
    @Operation(summary = "Get dress list by shop")
    public ResponseEntity<List<Dress>> getDressByShop(@PathVariable("shopId") String shopId, @PathVariable("offset") Integer offset,
                                                      @PathVariable("limit") Integer limit) throws DtsException {
        final var dressList = dressService.getDressByShop(shopId, limit, offset);
        if (!CollectionUtils.isEmpty(dressList)) {
            return ResponseEntity.ok(dressList);
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("no customer found")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());

    }

    @GetMapping("/v1/customer/{customerId}/{offset}/{limit}")
    @Operation(summary = "Get dress list by customer")
    public ResponseEntity<List<Dress>> getDressByCustomer(@PathVariable("customerId") String customerId, @PathVariable("offset") Integer offset,
                                                          @PathVariable("limit") Integer limit) throws DtsException {
        final var dressList = dressService.getDressByCustomer(customerId, limit, offset);
        if (!CollectionUtils.isEmpty(dressList)) {
            return ResponseEntity.ok(dressList);
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("no customer found")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());

    }

    @GetMapping("/v1/{dressId}")
    @Operation(summary = "Get dress by dress id")
    public ResponseEntity<Dress> getDressByDressId(@PathVariable("dressId") String dressId) throws DtsException {
        return ResponseEntity.ok(dressService.getDressByDressId(dressId).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("no customer found")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build())));
    }

    @PostMapping("/v1")
    @Operation(summary = "Add dress")
    public ResponseEntity<String> addDress(@RequestBody Dress dress) throws DtsException {
        dressService.addDress(dress).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build()));
        return ResponseEntity.ok("Dress added successfully");

    }

    @PutMapping("/v1")
    @Operation(summary = "Update dress")
    public ResponseEntity<String> updateDress(@RequestBody Dress dress) throws DtsException {
        dressService.updateDress(dress).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build()));
        return ResponseEntity.ok("Dress updated successfully");
    }
}
