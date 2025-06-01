package in.tailor.digi.main.repository;

import in.tailor.digi.model.Customer;
import in.tailor.digi.model.Shop;
import in.tailor.digi.model.User;
import in.tailor.digi.model.table.DtsColumn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerRepositoryImpl customerRepository;

    @Test
    void getCustomerByUser_returnsCustomerListWithoutShop() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Customer> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Customer customer = customerRowMapper.mapRow(rs, 0);
            return List.of(customer);
        });
        List<Customer> result = customerRepository.getCustomerByUser("TEST_USR_ID", 10, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("TEST_CUST_ID", result.get(0).getCustomerId());
    }

    @Test
    void getCustomerByUser_returnsCustomerListWithShop() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Customer> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.FIRST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MIDDLE_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.LAST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MOBILE_NO)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.EMAIL)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Customer customer = customerRowMapper.mapRow(rs, 0);
            return List.of(customer);
        });
        List<Customer> result = customerRepository.getCustomerByUser("TEST_USR_ID", 10, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("TEST_CUST_ID", result.get(0).getCustomerId());
        Assertions.assertEquals("TEST_SHP_ID", result.get(0).getShop().getShopId());
    }

    @Test
    void getCustomerByCustomerId_returnsCustomerPresent() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<Customer> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.FIRST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MIDDLE_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.LAST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MOBILE_NO)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.EMAIL)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_CUST_ID");
            return customerRowMapper.mapRow(rs, 0);
        });
        var result = customerRepository.getCustomerByCustomerId("TEST_CUST_ID");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("TEST_CUST_ID", result.get().getCustomerId());
    }

    @Test
    void getCustomerByCustomerId_returnsCustomerNotPresent() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenThrow(new EmptyResultDataAccessException(0));
        var result = customerRepository.getCustomerByCustomerId("TEST_CUST_ID");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void getCustomerByShop() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Customer> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.FIRST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MIDDLE_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.LAST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MOBILE_NO)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.EMAIL)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Customer customer = customerRowMapper.mapRow(rs, 0);
            return List.of(customer);
        });
        List<Customer> result = customerRepository.getCustomerByShop("TEST_SHP_ID", 10, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("TEST_CUST_ID", result.get(0).getCustomerId());
        Assertions.assertEquals("TEST_SHP_ID", result.get(0).getShop().getShopId());
    }

    @Test
    void addCustomer_withShop() {
        var customer = Customer.builder().customerId("TEST_CUST_ID").firstName("fn").middleName("mn").lastName("ln")
                .mobileNo("9090909090").email("email@email.com").createdAt(LocalDateTime.now())
                .shop(Shop.builder().shopId("TEST_SHP_ID").build())
                .user(User.builder().userId("TEST_USR_ID").build()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(1);
        var result = customerRepository.addCustomer(customer);
        Assertions.assertEquals(1, result);
    }

    @Test
    void addCustomer_withoutShop() {
        var customer = Customer.builder().customerId("TEST_CUST_ID").firstName("fn").middleName("mn").lastName("ln")
                .mobileNo("9090909090").email("email@email.com").createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.any(), Mockito.anyString()))
                .thenReturn(1);
        var result = customerRepository.addCustomer(customer);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateCustomer() {
        var customer = Customer.builder().customerId("TEST_CUST_ID").firstName("fn").middleName("mn").lastName("ln")
                .mobileNo("9090909090").email("email@email.com").createdAt(LocalDateTime.now())
                .shop(Shop.builder().shopId("TEST_SHP_ID").build())
                .user(User.builder().userId("TEST_USR_ID").build()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.any(LocalDateTime.class), Mockito.anyString()))
                .thenReturn(1);
        var result = customerRepository.updateCustomer(customer);
        Assertions.assertEquals(1, result);
    }
}