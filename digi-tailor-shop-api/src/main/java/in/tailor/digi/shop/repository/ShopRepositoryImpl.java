package in.tailor.digi.shop.repository;

import in.tailor.digi.model.Address;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 */
@Service
@Setter
public non-sealed class ShopRepositoryImpl implements ShopRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Shop> getShopByShopId(String shopId) {
        final String query = "SELECT DTS_SHOP.SHOP_ID, DTS_SHOP.SHOP_NAME, DTS_SHOP.SHOP_CODE, DTS_SHOP.SHOP_IMAGE, " +
                "DTS_SHOP.MOBILE_NO, DTS_SHOP.ALTERNATE_MOBILE_NO, DTS_SHOP.SHOP_STATUS, DTS_SHOP.OPEN_TIME, " +
                "DTS_SHOP.CLOSE_TIME, DTS_SHOP.HOLIDAY, DTS_ADDRESS.ADDRESS_ID, DTS_ADDRESS.ADD_LINE_ONE, " +
                "DTS_ADDRESS.ADD_LINE_TWO, DTS_ADDRESS.CITY, DTS_ADDRESS.STATE, DTS_ADDRESS.ZIPCODE FROM DTS_SHOP DTS_SHOP "
                + "LEFT JOIN DTS_ADDRESS DTS_ADDRESS ON DTS_SHOP.ADDRESS_ID = DTS_ADDRESS.ADDRESS_ID WHERE DTS_SHOP.SHOP_ID = ?";
        List<Shop> shopList = jdbcTemplate.query(query, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID))
                        .shopName(rs.getString(DtsColumn.SHOP_NAME)).shopCode(rs.getString(DtsColumn.SHOP_CODE))
                        .shopImage(rs.getString(DtsColumn.SHOP_IMAGE))
                        .mobileNo(rs.getString(DtsColumn.MOBILE_NO))
                        .alternateMobileNo(rs.getString(DtsColumn.ALTERNATE_MOBILE_NO))
                        .shopStatus(rs.getString(DtsColumn.SHOP_STATUS))
                        .openTime(rs.getObject(DtsColumn.OPEN_TIME, LocalTime.class))
                        .closeTime(rs.getObject(DtsColumn.CLOSE_TIME, LocalTime.class))
                        .holiday(rs.getString(DtsColumn.HOLIDAY))
                        .shopAddress(Address.builder().addressId(rs.getString(DtsColumn.ADDRESS_ID)).addressLineOne(rs.getString(DtsColumn.ADDRESS_LINE_ONE))
                                .addressLineTwo(rs.getString(DtsColumn.ADDRESS_LINE_TWO))
                                .city(rs.getString(DtsColumn.CITY)).state(rs.getString(DtsColumn.STATE))
                                .zipCode(rs.getString(DtsColumn.ZIPCODE)).build())
                        .build();
            }
        }, shopId);
        if (!CollectionUtils.isEmpty(shopList)) {
            return Optional.ofNullable(shopList.get(0));
        }
        return Optional.empty();
    }

    @Override
    public List<Shop> getShopList(int offset, int limit) {
        final String query = "SELECT DTS_SHOP.SHOP_ID, DTS_SHOP.SHOP_NAME, DTS_SHOP.SHOP_CODE, DTS_SHOP.CREATED_AT, DTS_SHOP.UPDATED_AT, DTS_ADDRESS.ADD_LINE_ONE, "
                + "DTS_ADDRESS.ADD_LINE_TWO, DTS_ADDRESS.CITY, DTS_ADDRESS.STATE, DTS_ADDRESS.ZIPCODE, DTS_USER.USER_ID FROM DTS_SHOP DTS_SHOP "
                + "LEFT JOIN DTS_USER_SHOP_MAPPING DTS_USER_SHOP_MAPPING ON DTS_SHOP.SHOP_ID = DTS_USER_SHOP_MAPPING.SHOP_ID "
                + "LEFT JOIN DTS_USER DTS_USER ON DTS_USER.USER_ID = DTS_USER_SHOP_MAPPING.USER_ID "
                + "LEFT JOIN DTS_ADDRESS DTS_ADDRESS ON DTS_SHOP.ADDRESS_ID = DTS_ADDRESS.ADDRESS_ID LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID))
                        .shopName(rs.getString(DtsColumn.SHOP_NAME)).shopCode(rs.getString(DtsColumn.SHOP_CODE))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .shopAddress(Address.builder().addressLineOne(rs.getString(DtsColumn.ADDRESS_LINE_ONE))
                                .addressLineTwo(rs.getString(DtsColumn.ADDRESS_LINE_TWO))
                                .city(rs.getString(DtsColumn.CITY)).state(rs.getString(DtsColumn.STATE))
                                .zipCode(rs.getString(DtsColumn.ZIPCODE)).build())
                        .shopOwner(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build()).build();

            }
        }, limit, offset);
    }

    @Override
    public List<Shop> getShopListByUser(String userId, int offset, int limit) {
        final String query = "SELECT DTS_SHOP.SHOP_ID, DTS_SHOP.SHOP_NAME, DTS_SHOP.SHOP_CODE, DTS_SHOP.SHOP_IMAGE, " +
                "DTS_SHOP.SHOP_STATUS, DTS_ADDRESS.ADDRESS_ID, DTS_ADDRESS.ADD_LINE_ONE, DTS_ADDRESS.ADD_LINE_TWO, DTS_ADDRESS.CITY, DTS_ADDRESS.STATE, " +
                "DTS_ADDRESS.ZIPCODE FROM DTS_SHOP DTS_SHOP "
                + "INNER JOIN DTS_USER_SHOP_MAPPING DTS_USER_SHOP_MAPPING ON DTS_SHOP.SHOP_ID = DTS_USER_SHOP_MAPPING.SHOP_ID "
                + "INNER JOIN DTS_USER DTS_USER ON DTS_USER.USER_ID = DTS_USER_SHOP_MAPPING.USER_ID "
                + "LEFT JOIN DTS_ADDRESS DTS_ADDRESS ON DTS_SHOP.ADDRESS_ID = DTS_ADDRESS.ADDRESS_ID WHERE DTS_USER.USER_ID = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID))
                        .shopName(rs.getString(DtsColumn.SHOP_NAME)).shopCode(rs.getString(DtsColumn.SHOP_CODE))
                        .shopImage(rs.getString(DtsColumn.SHOP_IMAGE)).shopStatus(rs.getString(DtsColumn.SHOP_STATUS))
                        .shopAddress(Address.builder().addressId(rs.getString(DtsColumn.ADDRESS_ID))
                                .addressLineOne(rs.getString(DtsColumn.ADDRESS_LINE_ONE))
                                .addressLineTwo(rs.getString(DtsColumn.ADDRESS_LINE_TWO))
                                .city(rs.getString(DtsColumn.CITY)).state(rs.getString(DtsColumn.STATE))
                                .zipCode(rs.getString(DtsColumn.ZIPCODE)).build())
                        .build();
            }
        }, userId, limit, offset);
    }

    @Override
    public int addShop(Shop shop) {
        return jdbcTemplate.update(
                "INSERT INTO DTS_SHOP (SHOP_ID, SHOP_NAME, SHOP_CODE, CREATED_AT, UPDATED_AT, ADDRESS_ID) VALUES (?, ?, ?, ?, ?, ?)",
                shop.getShopId(), shop.getShopName(), shop.getShopCode(), DtsUtils.getIndianCurrentDateTime(), null,
                Objects.nonNull(shop.getShopAddress()) ? shop.getShopAddress().getAddressId() : null);
    }

    @Override
    public int updateShop(Shop shop) {
        return jdbcTemplate.update("UPDATE DTS_SHOP SET SHOP_NAME= ?, UPDATED_AT = ? WHERE SHOP_ID = ?",
                shop.getShopName(), DtsUtils.getIndianCurrentDateTime(), shop.getShopId());
    }

    @Override
    public int deleteShopByShopId(int shopId) {
        return jdbcTemplate.update("DELETE FROM DTS_SHOP WHERE SHOP_ID = ?", shopId);
    }

    private RowMapper<Shop> shopRowMapper() {
        return new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Shop.builder().shopId(rs.getString(DtsColumn.SHOP_ID))
                        .shopName(rs.getString(DtsColumn.SHOP_NAME)).shopCode(rs.getString(DtsColumn.SHOP_CODE))
                        .createdAt(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class))
                        .updatedAt(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class))
                        .shopAddress(Address.builder().addressLineOne(rs.getString(DtsColumn.ADDRESS_LINE_ONE))
                                .addressLineTwo(rs.getString(DtsColumn.ADDRESS_LINE_TWO))
                                .city(rs.getString(DtsColumn.CITY)).state(rs.getString(DtsColumn.STATE))
                                .zipCode(rs.getString(DtsColumn.ZIPCODE)).build())
//						.shopOwner(User.builder().userId(rs.getString(DtsColumn.USER_ID)).build())
                        .build();
            }
        };
    }

}
