/**
 *
 */
package in.tailor.digi.shop.controller;

import in.tailor.digi.model.DtsApiResponse;
import in.tailor.digi.model.DtsException;
import in.tailor.digi.model.Shop;
import in.tailor.digi.shop.service.ShopService;
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
@RequestMapping("shop")
@Setter
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/test")
    @Operation(summary = "Test If Application is running")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("Shop Api working");
    }

    @GetMapping("/v1/{offset}/{limit}")
    @Operation(summary = "Get shop list")
    public ResponseEntity<DtsApiResponse<List<Shop>>> getShopList(@PathVariable("offset") Integer offset,
                                                                  @PathVariable("limit") Integer limit) throws DtsException {
        final var shopList = shopService.getAllShopList(offset, limit);
        if (!CollectionUtils.isEmpty(shopList)) {
            return ResponseEntity.ok(DtsApiResponse.<List<Shop>>builder().httpStatus(HttpStatus.OK.value())
                    .message("shop list Found").result(shopList).build());
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("no shop found")
                .httpStatus(HttpStatus.OK.value()).build());

    }

    @GetMapping("/v1/{userId}/{offset}/{limit}")
    @Operation(summary = "Get shop list by user")
    public ResponseEntity<DtsApiResponse<List<Shop>>> getShopListByUser(@PathVariable("userId") String userId,
                                                                        @PathVariable("offset") Integer offset, @PathVariable("limit") Integer limit) throws DtsException {
        final var shopList = shopService.getShopListByUser(userId, offset, limit);
        if (!CollectionUtils.isEmpty(shopList)) {
            return ResponseEntity.ok(DtsApiResponse.<List<Shop>>builder().httpStatus(HttpStatus.OK.value())
                    .message("shop list Found").result(shopList).build());
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("no shop found")
                .httpStatus(HttpStatus.OK.value()).build());

    }

    @PostMapping("/v1")
    @Operation(summary = "Add shop")
    public ResponseEntity<DtsApiResponse<Shop>> addShop(@RequestBody Shop shop) throws DtsException {
        final var optionalShop = shopService.addShop(shop);
        if (optionalShop.isPresent()) {
            return ResponseEntity.ok(DtsApiResponse.<Shop>builder().httpStatus(HttpStatus.OK.value())
                    .message("shop added successfully").build());
        }
        throw new DtsException(DtsApiResponse.<String>builder().httpStatus(HttpStatus.FORBIDDEN.value())
                .message("Shop not added. Please retry").build());

    }

    @PutMapping("/v1")
    @Operation(summary = "Update shop")
    public ResponseEntity<DtsApiResponse<Shop>> updateShop(@RequestBody Shop shop) throws DtsException {
        final var optionalShop = shopService.updateShop(shop);
        if (optionalShop.isPresent()) {
            return ResponseEntity.ok(DtsApiResponse.<Shop>builder().httpStatus(HttpStatus.OK.value())
                    .message("shop updated successfully").build());
        }
        throw new DtsException(DtsApiResponse.<String>builder().httpStatus(HttpStatus.FORBIDDEN.value())
                .message("Shop update failed. Please retry").build());
    }

    @PutMapping("/v1/address")
    @Operation(summary = "Update shop address")
    public ResponseEntity<DtsApiResponse<Shop>> updateShopAddress(@RequestBody Shop shop) throws DtsException {
        final var optionalShop = shopService.updateShopAddress(shop);
        if (optionalShop.isPresent()) {
            return ResponseEntity.ok(DtsApiResponse.<Shop>builder().httpStatus(HttpStatus.OK.value())
                    .message("Address updated successfully").build());
        }
        throw new DtsException(DtsApiResponse.<String>builder().httpStatus(HttpStatus.FORBIDDEN.value())
                .message("Shop update failed. Please retry").build());
    }

    @DeleteMapping("/v1/{shopId}")
    @Operation(summary = "Delete shop by id")
    public ResponseEntity<String> deleteShop(@PathVariable("shopId") Integer shopId) throws DtsException {
        final var isShopDeleted = shopService.deleteShopByShopId(shopId);
        if (isShopDeleted) {
            return ResponseEntity.ok("shop deleted successfully");
        }
        throw new DtsException(DtsApiResponse.<String>builder().httpStatus(HttpStatus.FORBIDDEN.value())
                .message("Shop not deleted. PLease retry").build());
    }
}
