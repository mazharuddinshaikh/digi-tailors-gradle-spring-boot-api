package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.CustomerRepositoryImpl;
import in.tailor.digi.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepositoryImpl customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getCustomerByUser() {
        var result = customerService.getCustomerByUser("USR_123", 10, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void getCustomerByCustomerId() {
        var result = customerService.getCustomerByUser("USR_123", 10, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void getCustomerByShop() {
        var result = customerService.getCustomerByUser("USR_123", 10, 0);
        Assertions.assertNotNull(result);
    }

    @Test
    void addCustomer_WhenCustomerAdded() {
        Customer customer = new Customer();
        Mockito.when(customerRepository.addCustomer(Mockito.any(Customer.class))).thenReturn(1);
        var result = customerService.addCustomer(customer);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void addCustomer_WhenCustomerNotAdded() {
        Customer customer = new Customer();
        Mockito.when(customerRepository.addCustomer(Mockito.any(Customer.class))).thenReturn(0);
        var result = customerService.addCustomer(customer);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void addCustomer_WhenCustomerUpdated() {
        Customer customer = new Customer();
        Mockito.when(customerRepository.updateCustomer(Mockito.any(Customer.class))).thenReturn(1);
        var result = customerService.updateCustomer(customer);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void addCustomer_WhenCustomerNotUpdated() {
        Customer customer = new Customer();
        Mockito.when(customerRepository.updateCustomer(Mockito.any(Customer.class))).thenReturn(0);
        var result = customerService.updateCustomer(customer);
        Assertions.assertTrue(result.isEmpty());
    }
}