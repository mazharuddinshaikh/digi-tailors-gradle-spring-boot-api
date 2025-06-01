package in.tailor.digi.main.repository;

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
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class AddressRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AddressRepositoryImpl addressRepository;

    @Test
    void updateShopAddress() {
        Shop shop = new Shop();
        shop.setShopId("TEST_ID");
        Address address = new Address();
        address.setAddressId("TEST_ID");
        shop.setShopAddress(address);
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString()))
                .thenReturn(1);
        var result = addressRepository.updateShopAddress(shop);
        Assertions.assertEquals(1, result);
    }

    @Test
    void addAddress() {
        Shop shop = new Shop();
        shop.setShopId("TEST_ID");
        Address address = new Address();
        address.setAddressId("TEST_ID");
        address.setAddressLineOne("line1");
        address.setAddressLineTwo("line2");
        address.setCity("Aurangabad");
        address.setState("Maharashtra");
        address.setZipCode("431");
        User shopOwner = new User();
        shopOwner.setUserId("test1");
        shop.setShopAddress(address);
        shop.setShopOwner(shopOwner);
        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(),
                Mockito.any(LocalDateTime.class), Mockito.any(), Mockito.anyString())).thenReturn(1);
        var result = addressRepository.addAddress(shop);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateAddress() {
        Address address = new Address();
        address.setAddressId("TEST_ID");
        address.setAddressLineOne("line1");
        address.setAddressLineTwo("line2");
        address.setCity("Aurangabad");
        address.setState("Maharashtra");
        address.setZipCode("431");
        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.anyString())).thenReturn(1);
        var result = addressRepository.updateAddress(address);
        Assertions.assertEquals(1, result);
    }
}