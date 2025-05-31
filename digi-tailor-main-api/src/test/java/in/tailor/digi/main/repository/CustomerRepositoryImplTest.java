package in.tailor.digi.main.repository;

import in.tailor.digi.model.Customer;
import in.tailor.digi.model.Shop;
import in.tailor.digi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerRepositoryImpl customerRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCustomerByUser_returnsCustomerList() {
        // Arrange
        String userId = "user123";
        int limit = 10;
        int offset = 0;

        Customer expectedCustomer = Customer.builder()
                .customerId("C001")
                .firstName("John")
                .middleName("M")
                .lastName("Doe")
                .mobileNo("9999999999")
                .email("john@example.com")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now())
                .user(User.builder().userId(userId).build())
                .shop(Shop.builder().shopId("SHOP01").build())
                .build();

        List<Customer> expectedCustomers = List.of(expectedCustomer);

        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.eq(userId),
                Mockito.eq(limit),
                Mockito.eq(offset)
        )).thenReturn(expectedCustomers);

        // Act
        List<Customer> result = customerRepository.getCustomerByUser(userId, limit, offset);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("C001", result.get(0).getCustomerId());
        Assertions.assertEquals("John", result.get(0).getFirstName());
        Assertions.assertEquals("SHOP01", result.get(0).getShop().getShopId());
    }
}