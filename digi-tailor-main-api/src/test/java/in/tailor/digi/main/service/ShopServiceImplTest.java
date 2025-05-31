package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.AddressRepositoryImpl;
import in.tailor.digi.main.repository.ShopRepositoryImpl;
import in.tailor.digi.main.repository.UserShopMappingRepositoryImpl;
import in.tailor.digi.model.Address;
import in.tailor.digi.model.Shop;
import in.tailor.digi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ShopServiceImplTest {
    @Mock
    private ShopRepositoryImpl shopRepository;
    @Mock
    private UserShopMappingRepositoryImpl userShopMappingRepository;
    @Mock
    private AddressRepositoryImpl addressRepository;
    @InjectMocks
    private ShopServiceImpl shopService;

    @Test
    void getShopByShopId() {
        var result = shopService.getShopByShopId("USR_123");
        Assertions.assertNotNull(result);
    }

    @Test
    void getAllShopList() {
        var result = shopService.getAllShopList(10, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void getShopListByUser() {
        var result = shopService.getShopListByUser("USR_123", 0, 10);
        Assertions.assertNotNull(result);
    }


    @Test
    void addShop_WithAddressWhenSuccessFull() {
        User user = new User();
        user.setUserId("TEST_USR_1");
        Shop shop = new Shop();
        shop.setShopOwner(user);
        Address shopAddress = new Address();
        shop.setShopAddress(shopAddress);
        Mockito.when(shopRepository.addShop(Mockito.any(Shop.class))).thenReturn(1);
        var result = shopService.addShop(shop);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void addShop_WithoutAddressWhenSuccessFull() {
        User user = new User();
        user.setUserId("TEST_USR_1");
        Shop shop = new Shop();
        shop.setShopOwner(user);
        Mockito.when(shopRepository.addShop(Mockito.any(Shop.class))).thenReturn(1);
        var result = shopService.addShop(shop);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void addShop_WhenFailed() {
        User user = new User();
        user.setUserId("TEST_USR_1");
        Shop shop = new Shop();
        shop.setShopOwner(user);
        Mockito.when(shopRepository.addShop(Mockito.any(Shop.class))).thenReturn(0);
        var result = shopService.addShop(shop);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void updateShop_WhenSuccessFull() {
        Shop shop = new Shop();
        shop.setShopId("TEST_SHP_1");
        Mockito.when(shopRepository.getShopByShopId(Mockito.anyString())).thenReturn(Optional.of(shop));
        Mockito.when(shopRepository.updateShop(Mockito.any(Shop.class))).thenReturn(1);
        var result = shopService.updateShop(shop);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void updateShop_WhenFailed() {
        Shop shop = new Shop();
        shop.setShopId("TEST_SHP_1");
        Mockito.when(shopRepository.getShopByShopId(Mockito.anyString())).thenReturn(Optional.of(shop));
        Mockito.when(shopRepository.updateShop(Mockito.any(Shop.class))).thenReturn(0);
        var result = shopService.updateShop(shop);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void updateShopAddress_WhenAddressAvailable() {
        Shop shop = new Shop();
        Address shopAddress = new Address();
        shopAddress.setAddressId("TEST_ADD_1");
        shop.setShopAddress(shopAddress);
        var result = shopService.updateShopAddress(shop);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void updateShopAddress_WhenAddressNotAvailable() {
        Shop shop = new Shop();
        Address shopAddress = new Address();
        shop.setShopAddress(shopAddress);
        var result = shopService.updateShopAddress(shop);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void deleteShopByShopId_WhenSuccessFull() {
        Mockito.when(shopRepository.deleteShopByShopId(Mockito.anyString())).thenReturn(1);
        var result = shopService.deleteShopByShopId("TEST_SHP_1");
        Assertions.assertTrue(result);
    }

    @Test
    void deleteShopByShopId_WhenFailed() {
        Mockito.when(shopRepository.deleteShopByShopId(Mockito.anyString())).thenReturn(0);
        var result = shopService.deleteShopByShopId("TEST_SHP_1");
        Assertions.assertFalse(result);
    }

}