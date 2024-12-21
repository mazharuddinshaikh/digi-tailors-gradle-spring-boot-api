package in.tailor.digi.customer.repository;

import in.tailor.digi.model.Customer;
import in.tailor.digi.model.Shop;
import in.tailor.digi.model.User;
import in.tailor.digi.model.table.DtsColumn;
import in.tailor.digi.utils.DtsUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Setter
public non-sealed class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomerByUser(String userId, int limit, int offset) {
        final String query = "SELECT DTS_CUSTOMER.CUSTOMER_ID, DTS_CUSTOMER.FIRST_NAME, DTS_CUSTOMER.MIDDLE_NAME, DTS_CUSTOMER.LAST_NAME, " +
                "DTS_CUSTOMER.MOBILE_NO, DTS_CUSTOMER.EMAIL, DTS_CUSTOMER.CREATED_AT, DTS_CUSTOMER.UPDATED_AT, DTS_CUSTOMER.USER_ID, " +
                "DTS_CUSTOMER.SHOP_ID FROM DTS_CUSTOMER DTS_CUSTOMER WHERE DTS_CUSTOMER.USER_ID = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Customer.builder().customerId(rs.getString(DtsColumn.CUSTOMER_ID))
                        .firstName(rs.getString(DtsColumn.FIRST_NAME)).middleName(rs.getString(DtsColumn.MIDDLE_NAME))
                        .lastName(rs.getString(DtsColumn.LAST_NAME)).mobileNo(rs.getString(DtsColumn.MOBILE_NO))
                        .email(rs.getString(DtsColumn.EMAIL))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .user(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build())
                        .shop(Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID)).build())
                        .build();
            }
        }, userId, limit, offset);
    }

    @Override
    public List<Customer> getCustomerByShop(String shopId, int limit, int offset) {
        final String query = "SELECT DTS_CUSTOMER.CUSTOMER_ID, DTS_CUSTOMER.FIRST_NAME, DTS_CUSTOMER.MIDDLE_NAME, DTS_CUSTOMER.LAST_NAME, " +
                "DTS_CUSTOMER.MOBILE_NO, DTS_CUSTOMER.EMAIL, DTS_CUSTOMER.CREATED_AT, DTS_CUSTOMER.UPDATED_AT, DTS_CUSTOMER.USER_ID, " +
                "DTS_CUSTOMER.SHOP_ID FROM DTS_CUSTOMER DTS_CUSTOMER WHERE DTS_CUSTOMER.SHOP_ID = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Customer.builder().customerId(rs.getString(DtsColumn.CUSTOMER_ID))
                        .firstName(rs.getString(DtsColumn.FIRST_NAME)).middleName(rs.getString(DtsColumn.MIDDLE_NAME))
                        .lastName(rs.getString(DtsColumn.LAST_NAME)).mobileNo(rs.getString(DtsColumn.MOBILE_NO))
                        .email(rs.getString(DtsColumn.EMAIL))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .user(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build())
                        .shop(Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID)).build())
                        .build();
            }
        }, shopId, limit, offset);
    }

    @Override
    public int addCustomer(Customer customer) {
        return jdbcTemplate.update(
                "INSERT INTO DTS_CUSTOMER (CUSTOMER_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, MOBILE_NO, EMAIL, CREATED_AT, " +
                        "SHOP_ID, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                customer.getCustomerId(), customer.getFirstName(), customer.getMiddleName(),
                customer.getLastName(), customer.getMobileNo(), customer.getEmail(), DtsUtils.getIndianCurrentDateTime(),
                Objects.nonNull(customer.getShop()) ? customer.getShop().getShopId() : null, customer.getUser().getUserId());
    }

    @Override
    public int updateCustomer(Customer customer) {
        return jdbcTemplate.update("UPDATE DTS_CUSTOMER SET FIRST_NAME= ?, MIDDLE_NAME = ?, LAST_NAME = ?, " +
                        "MOBILE_NO = ?, EMAIL = ?, UPDATED_AT = ? WHERE CUSTOMER_ID = ?",
                customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getMobileNo(),
                customer.getEmail(), DtsUtils.getIndianCurrentDateTime(), customer.getCustomerId());
    }
}
