package in.tailor.digi.main.controller;

import in.tailor.digi.main.service.ShopServiceImpl;
import in.tailor.digi.model.DtsApiResponse;
import in.tailor.digi.model.DtsException;
import in.tailor.digi.model.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ShopControllerTest {
    @Mock
    private ShopServiceImpl shopService;
    @InjectMocks
    private ShopController shopController;


    @Test
    public void testApi_ShouldReturnOk() throws Exception {
        var result = shopController.testApi();
        Assertions.assertEquals("Shop Api working", result.getBody());
    }

    @Test
    void getShopList_ShouldReturnShopList() throws DtsException {
        int offset = 0;
        int limit = 10;
        Shop shop = new Shop();
        List<Shop> mockList = List.of(shop);
        Mockito.when(shopService.getAllShopList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockList);
        ResponseEntity<List<Shop>> response = shopController.getShopList(offset, limit);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(shopService).getAllShopList(offset, limit);
    }

    @Test
    void getShopList_ShouldThrowDtsException_WhenEmpty() {
        int offset = 0;
        int limit = 10;
        Mockito.when(shopService.getAllShopList(offset, limit)).thenReturn(List.of());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                shopController.getShopList(offset, limit)
        );
        Assertions.assertEquals(204, thrown.getResponse().getHttpStatus());
        Mockito.verify(shopService).getAllShopList(offset, limit);
    }

    @Test
    void getShopListByUser_ShouldReturnShopList() throws DtsException {
        String userId = "DTS_USR_123";
        int offset = 0;
        int limit = 10;
        Shop shop = new Shop();
        List<Shop> mockList = List.of(shop);
        Mockito.when(shopService.getShopListByUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockList);
        ResponseEntity<List<Shop>> response = shopController.getShopListByUser(userId, offset, limit);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(shopService).getShopListByUser(userId, offset, limit);
    }

    @Test
    void getShopListByUser_ShouldThrowDtsException_WhenEmpty() {
        String userId = "DTS_USR_123";
        int offset = 0;
        int limit = 10;
        Mockito.when(shopService.getShopListByUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(List.of());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                shopController.getShopListByUser(userId, offset, limit)
        );
        Assertions.assertEquals(204, thrown.getResponse().getHttpStatus());
        Mockito.verify(shopService).getShopListByUser(userId, offset, limit);
    }

    @Test
    void getShopByShopId_ShouldReturnShop() throws DtsException {
        String shopId = "DTS_SHP_123";
        Shop shop = new Shop();
        Mockito.when(shopService.getShopByShopId(Mockito.anyString())).thenReturn(Optional.of(shop));
        ResponseEntity<Shop> response = shopController.getShopByShopId(shopId);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(shopService).getShopByShopId(shopId);
    }

    @Test
    void getShopByShopId_ShouldThrowDtsException_WhenEmpty() {
        String shopId = "DTS_SHP_123";
        int offset = 0;
        int limit = 10;
        Mockito.when(shopService.getShopByShopId(Mockito.anyString())).thenReturn(Optional.empty());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                shopController.getShopByShopId(shopId)
        );
        Assertions.assertEquals(204, thrown.getResponse().getHttpStatus());
        Mockito.verify(shopService).getShopByShopId(shopId);
    }

    @Test
    void shouldReturnSuccessResponseWhenShopIsAdded() throws DtsException {
        Shop shop = new Shop();
        // Arrange
        Mockito.when(shopService.addShop(Mockito.any(Shop.class))).thenReturn(Optional.of(shop));
        // Act
        ResponseEntity<DtsApiResponse<Shop>> response = shopController.addShop(shop);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("shop added successfully", response.getBody().getMessage());
    }

    @Test
    void shouldThrowDtsExceptionWhenShopNotAdded() {
        Shop shop = new Shop();
        // Arrange
        Mockito.when(shopService.addShop(Mockito.any(Shop.class))).thenReturn(Optional.empty());
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> {
            shopController.addShop(shop);
        });
        Assertions.assertEquals("Shop not added. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldReturnSuccessResponseWhenShopIsUpdated() throws DtsException {
        Shop shop = new Shop();
        // Arrange
        Mockito.when(shopService.updateShop(Mockito.any(Shop.class))).thenReturn(Optional.of(shop));
        // Act
        ResponseEntity<DtsApiResponse<Shop>> response = shopController.updateShop(shop);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("shop updated successfully", response.getBody().getMessage());
    }

    @Test
    void shouldThrowDtsExceptionWhenDressNotUpdated() {
        Shop shop = new Shop();
        // Arrange
        Mockito.when(shopService.updateShop(Mockito.any(Shop.class))).thenReturn(Optional.empty());
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> {
            shopController.updateShop(shop);
        });
        Assertions.assertEquals("Shop update failed. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldReturnSuccessResponseWhenShopAddressIsUpdated() throws DtsException {
        Shop shop = new Shop();
        // Arrange
        Mockito.when(shopService.updateShopAddress(Mockito.any(Shop.class))).thenReturn(Optional.of(shop));
        // Act
        ResponseEntity<DtsApiResponse<Shop>> response = shopController.updateShopAddress(shop);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Address updated successfully", response.getBody().getMessage());
    }

    @Test
    void shouldThrowDtsExceptionWhenShopAddressNotUpdated() {
        Shop shop = new Shop();
        // Arrange
        Mockito.when(shopService.updateShopAddress(Mockito.any(Shop.class))).thenReturn(Optional.empty());
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> {
            shopController.updateShopAddress(shop);
        });
        Assertions.assertEquals("Address update failed. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void deleteShopByShopId_ShouldDeleteShop() throws DtsException {
        String shopId = "DTS_SHP_123";
        Shop shop = new Shop();
        Mockito.when(shopService.deleteShopByShopId(Mockito.anyString())).thenReturn(true);
        ResponseEntity<String> response = shopController.deleteShopByShopId(shopId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("shop deleted successfully", response.getBody());
        Mockito.verify(shopService).deleteShopByShopId(shopId);
    }

    @Test
    void deleteShopByShopId_ShouldThrowDtsException_WhenEmpty() {
        String shopId = "DTS_SHP_123";
        int offset = 0;
        int limit = 10;
        Mockito.when(shopService.deleteShopByShopId(Mockito.anyString())).thenReturn(false);
        DtsException exception = Assertions.assertThrows(DtsException.class, () ->
                shopController.deleteShopByShopId(shopId)
        );
        Assertions.assertEquals("Shop not deleted. PLease retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
        Mockito.verify(shopService).deleteShopByShopId(shopId);
    }
}