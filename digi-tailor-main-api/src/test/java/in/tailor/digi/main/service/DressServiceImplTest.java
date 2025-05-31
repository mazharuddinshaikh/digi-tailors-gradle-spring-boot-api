package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.DressRepositoryImpl;
import in.tailor.digi.model.Dress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DressServiceImplTest {
    @Mock
    private DressRepositoryImpl dressRepository;
    @InjectMocks
    private DressServiceImpl dressService;

    @Test
    void getDressByUser() {
        var result = dressService.getDressByUser("USR_123", 10, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void getDressByShop() {
        var result = dressService.getDressByShop("USR_123", 10, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void getDressByCustomer() {
        var result = dressService.getDressByCustomer("USR_123", 10, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void getDressByDressId() {
        var result = dressService.getDressByDressId("USR_123");
        Assertions.assertNotNull(result);
    }

    @Test
    void addDress_WhenSuccessFull() {
        Dress dress = new Dress();
        Mockito.when(dressRepository.addDress(Mockito.any(Dress.class))).thenReturn(1);
        var result = dressService.addDress(dress);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void addDress_WhenFailed() {
        Dress dress = new Dress();
        Mockito.when(dressRepository.addDress(Mockito.any(Dress.class))).thenReturn(0);
        var result = dressService.addDress(dress);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void updateDress_WhenSuccessFull() {
        Dress dress = new Dress();
        Mockito.when(dressRepository.updateDress(Mockito.any(Dress.class))).thenReturn(1);
        var result = dressService.updateDress(dress);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void update_WhenFailed() {
        Dress dress = new Dress();
        Mockito.when(dressRepository.updateDress(Mockito.any(Dress.class))).thenReturn(0);
        var result = dressService.updateDress(dress);
        Assertions.assertTrue(result.isEmpty());
    }
}