package in.tailor.digi.dress.repository;


import in.tailor.digi.model.Customer;
import in.tailor.digi.model.Dress;
import in.tailor.digi.model.Shop;
import in.tailor.digi.model.User;
import in.tailor.digi.model.table.DtsColumn;
import in.tailor.digi.utils.DtsUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Setter
public non-sealed class DressRepositoryImpl implements DressRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Dress> getDressByUser(String userId, int limit, int offset) {
        String query = "SELECT DTS_DRESS.DRESS_ID, DTS_DRESS.USER_ID, DTS_DRESS.SHOP_ID, DTS_DRESS.CUSTOMER_ID, " +
                "DTS_DRESS.COMMENT, DTS_DRESS.CREATED_AT, DTS_DRESS.UPDATED_AT FROM DTS_DRESS DTS_DRESS WHERE DTS_DRESS.USER_ID = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new RowMapper<Dress>() {
            @Override
            public Dress mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Dress.builder()
                        .dressId(rs.getString(DtsColumn.DRESS_ID))
                        .user(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build())
                        .shop(Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID)).build())
                        .customer(Customer.builder().customerId(rs.getString(DtsColumn.CUSTOMER_ID)).build())
                        .comment(rs.getString(DtsColumn.COMMENT))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .build();
            }
        }, userId, limit, offset);
    }

    @Override
    public List<Dress> getDressByShop(String shopId, int limit, int offset) {
        String query = "SELECT DTS_DRESS.DRESS_ID, DTS_DRESS.USER_ID, DTS_DRESS.SHOP_ID, DTS_DRESS.CUSTOMER_ID, " +
                "DTS_DRESS.COMMENT, DTS_DRESS.CREATED_AT, DTS_DRESS.UPDATED_AT FROM DTS_DRESS DTS_DRESS WHERE DTS_DRESS.SHOP_ID = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new RowMapper<Dress>() {
            @Override
            public Dress mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Dress.builder()
                        .dressId(rs.getString(DtsColumn.DRESS_ID))
                        .user(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build())
                        .shop(Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID)).build())
                        .customer(Customer.builder().customerId(rs.getString(DtsColumn.CUSTOMER_ID)).build())
                        .comment(rs.getString(DtsColumn.COMMENT))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .build();
            }
        }, shopId, limit, offset);
    }

    @Override
    public List<Dress> getDressByCustomer(String customerId, int limit, int offset) {
        String query = "SELECT DTS_DRESS.DRESS_ID, DTS_DRESS.USER_ID, DTS_DRESS.SHOP_ID, DTS_DRESS.CUSTOMER_ID, " +
                "DTS_DRESS.COMMENT, DTS_DRESS.CREATED_AT, DTS_DRESS.UPDATED_AT FROM DTS_DRESS DTS_DRESS WHERE DTS_DRESS.CUSTOMER_ID = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new RowMapper<Dress>() {
            @Override
            public Dress mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Dress.builder()
                        .dressId(rs.getString(DtsColumn.DRESS_ID))
                        .user(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build())
                        .shop(Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID)).build())
                        .customer(Customer.builder().customerId(rs.getString(DtsColumn.CUSTOMER_ID)).build())
                        .comment(rs.getString(DtsColumn.COMMENT))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .build();
            }
        }, customerId, limit, offset);
    }

    @Override
    public Optional<Dress> getDressByDressId(String dressId) {
        String query = "SELECT DTS_DRESS.DRESS_ID, DTS_DRESS.USER_ID, DTS_DRESS.SHOP_ID, DTS_DRESS.CUSTOMER_ID, " +
                "DTS_DRESS.COMMENT, DTS_DRESS.CREATED_AT, DTS_DRESS.UPDATED_AT FROM DTS_DRESS DTS_DRESS WHERE DTS_DRESS.DRESS_ID = ?";
        final var dresList = jdbcTemplate.query(query, new RowMapper<Dress>() {
            @Override
            public Dress mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Dress.builder()
                        .dressId(rs.getString(DtsColumn.DRESS_ID))
                        .user(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build())
                        .shop(Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID)).build())
                        .customer(Customer.builder().customerId(rs.getString(DtsColumn.CUSTOMER_ID)).build())
                        .comment(rs.getString(DtsColumn.COMMENT))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .build();
            }
        }, dressId);
        if (!CollectionUtils.isEmpty(dresList)) {
            return Optional.of(dresList.get(0));
        }
        return Optional.empty();
    }

    @Override
    public int addDress(Dress dress) {
        return jdbcTemplate.update(
                "INSERT INTO DTS_DRESS (DRESS_ID, USER_ID, SHOP_ID, CUSTOMER_ID, COMMENT, CREATED_AT) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                dress.getDressId(), dress.getUser().getUserId(), Objects.nonNull(dress.getShop()) ? dress.getShop().getShopId() : null,
                Objects.nonNull(dress.getCustomer()) ? dress.getCustomer().getCustomerId() : null, dress.getComment(),
                DtsUtils.getIndianCurrentDateTime());
    }

    @Override
    public int updateDress(Dress dress) {
        return jdbcTemplate.update("UPDATE DTS_DRESS SET SHOP_ID = ?, CUSTOMER_ID = ?, COMMENT = ?, UPDATED_AT = ? WHERE DRESS_ID = ?",
                Objects.nonNull(dress.getShop()) ? dress.getShop().getShopId() : null,
                Objects.nonNull(dress.getCustomer()) ? dress.getCustomer().getCustomerId() : null, dress.getComment(),
                DtsUtils.getIndianCurrentDateTime(), dress.getDressId());
    }
}
