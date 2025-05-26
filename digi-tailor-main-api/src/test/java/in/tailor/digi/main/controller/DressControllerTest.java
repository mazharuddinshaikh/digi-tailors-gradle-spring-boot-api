package in.tailor.digi.main.controller;

import in.tailor.digi.main.service.DressServiceImpl;
import in.tailor.digi.model.Dress;
import in.tailor.digi.model.DtsException;
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
class DressControllerTest {

    @Mock
    private DressServiceImpl dressService;
    @InjectMocks
    private DressController dressController;

    @Test
    public void testApi_ShouldReturnOk() {
        var result = dressController.testApi();
        Assertions.assertEquals("Dress Api working", result.getBody());
    }

    @Test
    void getDressByUser_ShouldReturnDressList() throws DtsException {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Dress dress = new Dress();
        List<Dress> mockList = List.of(dress);
        Mockito.when(dressService.getDressByUser(userId, limit, offset)).thenReturn(mockList);
        ResponseEntity<List<Dress>> response = dressController.getDressByUser(userId, offset, limit);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(dressService).getDressByUser(userId, limit, offset);
    }

    @Test
    void getDressByUser_ShouldThrowDtsException_WhenEmpty() {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Mockito.when(dressService.getDressByUser(userId, limit, offset)).thenReturn(List.of());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                dressController.getDressByUser(userId, offset, limit)
        );
        Assertions.assertEquals(403, thrown.getResponse().getHttpStatus());
        Mockito.verify(dressService).getDressByUser(userId, limit, offset);
    }

    @Test
    void getDressByShop_ShouldReturnDressList() throws DtsException {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Dress dress = new Dress();
        List<Dress> mockList = List.of(dress);
        Mockito.when(dressService.getDressByShop(userId, limit, offset)).thenReturn(mockList);
        ResponseEntity<List<Dress>> response = dressController.getDressByShop(userId, offset, limit);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(dressService).getDressByShop(userId, limit, offset);
    }

    @Test
    void getDressByShop_ShouldThrowDtsException_WhenEmpty() {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Mockito.when(dressService.getDressByShop(userId, limit, offset)).thenReturn(List.of());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                dressController.getDressByShop(userId, offset, limit)
        );
        Assertions.assertEquals(403, thrown.getResponse().getHttpStatus());
        Mockito.verify(dressService).getDressByShop(userId, limit, offset);
    }

    @Test
    void getDressByCustomer_ShouldReturnDressList() throws DtsException {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Dress dress = new Dress();
        List<Dress> mockList = List.of(dress);
        Mockito.when(dressService.getDressByCustomer(userId, limit, offset)).thenReturn(mockList);
        ResponseEntity<List<Dress>> response = dressController.getDressByCustomer(userId, offset, limit);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(dressService).getDressByCustomer(userId, limit, offset);
    }

    @Test
    void getDressByCustomer_ShouldThrowDtsException_WhenEmpty() {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Mockito.when(dressService.getDressByCustomer(userId, limit, offset)).thenReturn(List.of());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                dressController.getDressByCustomer(userId, offset, limit)
        );
        Assertions.assertEquals(403, thrown.getResponse().getHttpStatus());
        Mockito.verify(dressService).getDressByCustomer(userId, limit, offset);
    }

    @Test
    void shouldReturnDressWhenFound() throws DtsException {
        // Arrange
        String customerId = "DTS_CUST_123";
        Dress dress = new Dress();
        Mockito.when(dressService.getDressByDressId(Mockito.anyString()))
                .thenReturn(Optional.of(dress));
        // Act
        ResponseEntity<Dress> response = dressController.getDressByDressId(customerId);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(dress, response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenCustomerNotFound() {
        // Arrange
        Mockito.when(dressService.getDressByDressId(Mockito.anyString()))
                .thenReturn(Optional.empty());
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> dressController.getDressByDressId("DTS_CUST_123"));
        Assertions.assertEquals("no dress found", exception.getResponse().getMessage());
        Assertions.assertEquals(403, exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldReturnSuccessResponseWhenDressIsAdded() throws DtsException {
        Dress dress = new Dress();
        // Arrange
        Mockito.when(dressService.addDress(Mockito.any(Dress.class))).thenReturn(Optional.of(dress));

        // Act
        ResponseEntity<String> response = dressController.addDress(dress);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Dress added successfully", response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenDressNotAdded() {
        Dress dress = new Dress();
        // Arrange
        Mockito.when(dressService.addDress(Mockito.any(Dress.class))).thenReturn(Optional.empty());

        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> dressController.addDress(dress));

        Assertions.assertEquals("Something went wrong. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldReturnSuccessResponseWhenDressIsUpdated() throws DtsException {
        Dress dress = new Dress();
        // Arrange
        Mockito.when(dressService.updateDress(Mockito.any(Dress.class))).thenReturn(Optional.of(dress));

        // Act
        ResponseEntity<String> response = dressController.updateDress(dress);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Dress updated successfully", response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenDressNotUpdated() {
        Dress dress = new Dress();
        // Arrange
        Mockito.when(dressService.updateDress(Mockito.any(Dress.class))).thenReturn(Optional.empty());

        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> dressController.updateDress(dress));

        Assertions.assertEquals("Something went wrong. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }
}