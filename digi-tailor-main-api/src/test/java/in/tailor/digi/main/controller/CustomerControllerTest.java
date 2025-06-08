package in.tailor.digi.main.controller;

import in.tailor.digi.main.service.CustomerServiceImpl;
import in.tailor.digi.model.Customer;
import in.tailor.digi.model.DtsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class CustomerControllerTest {


    @Mock
    private CustomerServiceImpl customerService;
    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setup() {
    }

    @Test
    public void testApi_ShouldReturnOk() {
        var result = customerController.testApi();
        Assertions.assertEquals("Customer Api working", result.getBody());
    }

    @Test
    void shouldReturnCustomerWhenFound() throws DtsException {
        // Arrange
        String customerId = "DTS_CUST_123";
        Customer sampleCustomer = new Customer();
        Mockito.when(customerService.getCustomerByCustomerId(Mockito.anyString()))
                .thenReturn(Optional.of(sampleCustomer));
        // Act
        ResponseEntity<Customer> response = customerController.getCustomerByCustomerId(customerId);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(sampleCustomer, response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenCustomerNotFound() {
        // Arrange
        Mockito.when(customerService.getCustomerByCustomerId(Mockito.anyString()))
                .thenReturn(Optional.empty());
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> customerController.getCustomerByCustomerId("DTS_CUST_123"));
        Assertions.assertEquals("customer not found", exception.getResponse().getMessage());
        Assertions.assertEquals(204, exception.getResponse().getHttpStatus());
    }

    @Test
    void getCustomerByUser_ShouldReturnCustomerList() throws DtsException {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Customer mockCustomer = new Customer(); // Assuming a default constructor
        List<Customer> mockList = List.of(mockCustomer);
        Mockito.when(customerService.getCustomerByUser(userId, offset, limit)).thenReturn(mockList);
        ResponseEntity<List<Customer>> response = customerController.getCustomerByUser(userId, offset, limit);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(customerService).getCustomerByUser(userId, offset, limit);
    }

    @Test
    void getCustomerByUser_ShouldThrowDtsException_WhenEmpty() {
        String userId = "user123";
        int offset = 0;
        int limit = 10;
        Mockito.when(customerService.getCustomerByUser(userId, offset, limit)).thenReturn(List.of());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                customerController.getCustomerByUser(userId, offset, limit)
        );
        Assertions.assertEquals(204, thrown.getResponse().getHttpStatus());
        Mockito.verify(customerService).getCustomerByUser(userId, offset, limit);
    }

    @Test
    void getCustomerByShop_ShouldReturnCustomerList() throws DtsException {
        String shopId = "user123";
        int offset = 0;
        int limit = 10;
        Customer mockCustomer = new Customer(); // Assuming a default constructor
        List<Customer> mockList = List.of(mockCustomer);
        Mockito.when(customerService.getCustomerByShop(shopId, offset, limit)).thenReturn(mockList);
        ResponseEntity<List<Customer>> response = customerController.getCustomerByShop(shopId, offset, limit);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(customerService).getCustomerByShop(shopId, offset, limit);
    }

    @Test
    void getCustomerByShop_ShouldThrowDtsException_WhenEmpty() {
        String shopId = "user123";
        int offset = 0;
        int limit = 10;
        Mockito.when(customerService.getCustomerByShop(shopId, offset, limit)).thenReturn(List.of());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                customerController.getCustomerByShop(shopId, offset, limit)
        );
        Assertions.assertEquals(204, thrown.getResponse().getHttpStatus());
        Mockito.verify(customerService).getCustomerByShop(shopId, offset, limit);
    }

    @Test
    void shouldReturnSuccessResponseWhenCustomerIsAdded() throws DtsException {
        Customer mockCustomer = new Customer();
        // Arrange
        Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(Optional.of(mockCustomer));

        // Act
        ResponseEntity<String> response = customerController.addCustomer(mockCustomer);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Customer added successfully", response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenCustomerNotAdded() {
        Customer mockCustomer = new Customer();
        // Arrange
        Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(Optional.empty());

        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> customerController.addCustomer(mockCustomer));

        Assertions.assertEquals("Something went wrong. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldReturnSuccessResponseWhenCustomerIsUpdated() throws DtsException {
        Customer mockCustomer = new Customer();
        // Arrange
        Mockito.when(customerService.updateCustomer(Mockito.any(Customer.class))).thenReturn(Optional.of(mockCustomer));

        // Act
        ResponseEntity<String> response = customerController.updateCustomer(mockCustomer);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Customer updated successfully", response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenCustomerNotUpdated() {
        Customer mockCustomer = new Customer();
        // Arrange
        Mockito.when(customerService.updateCustomer(Mockito.any(Customer.class))).thenReturn(Optional.empty());

        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> customerController.updateCustomer(mockCustomer));

        Assertions.assertEquals("Something went wrong. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

}
