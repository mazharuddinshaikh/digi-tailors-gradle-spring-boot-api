package in.tailor.digi.main.repository;

import in.tailor.digi.model.Address;
import in.tailor.digi.model.Shop;
import in.tailor.digi.model.table.DtsColumn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ShopRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ShopRepositoryImpl shopRepository;

    @Test
    void getShopByShopId_withAddress() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<Shop> shopRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_NAME)).thenReturn("sn");
            Mockito.when(rs.getString(DtsColumn.SHOP_CODE)).thenReturn("sc");
            Mockito.when(rs.getString(DtsColumn.SHOP_IMAGE)).thenReturn("si");
            Mockito.when(rs.getString(DtsColumn.MOBILE_NO)).thenReturn("9090909090");
            Mockito.when(rs.getString(DtsColumn.ALTERNATE_MOBILE_NO)).thenReturn("9090909090");
            Mockito.when(rs.getString(DtsColumn.SHOP_STATUS)).thenReturn("ss");
            Mockito.when(rs.getObject(DtsColumn.OPEN_TIME, LocalTime.class)).thenReturn(LocalTime.now());
            Mockito.when(rs.getObject(DtsColumn.CLOSE_TIME, LocalTime.class)).thenReturn(LocalTime.now());
            Mockito.when(rs.getString(DtsColumn.HOLIDAY)).thenReturn("h");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn("TEST_ADD_ID");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn("TEST_ADD_ID");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_LINE_ONE)).thenReturn("al1");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_LINE_TWO)).thenReturn("al2");
            Mockito.when(rs.getString(DtsColumn.CITY)).thenReturn("c");
            Mockito.when(rs.getString(DtsColumn.STATE)).thenReturn("s");
            Mockito.when(rs.getString(DtsColumn.ZIPCODE)).thenReturn("zc");
            Shop shop = shopRowMapper.mapRow(rs, 0);
            return List.of(shop);
        });
        var result = shopRepository.getShopByShopId("TEST_SHP_ID");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("TEST_SHP_ID", result.get().getShopId());
    }

    @Test
    void getShopByShopId_withoutAddress() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<Shop> shopRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_NAME)).thenReturn("sn");
            Mockito.when(rs.getString(DtsColumn.SHOP_CODE)).thenReturn("sc");
            Mockito.when(rs.getString(DtsColumn.SHOP_IMAGE)).thenReturn("si");
            Mockito.when(rs.getString(DtsColumn.MOBILE_NO)).thenReturn("9090909090");
            Mockito.when(rs.getString(DtsColumn.ALTERNATE_MOBILE_NO)).thenReturn("9090909090");
            Mockito.when(rs.getString(DtsColumn.SHOP_STATUS)).thenReturn("ss");
            Mockito.when(rs.getObject(DtsColumn.OPEN_TIME, LocalTime.class)).thenReturn(LocalTime.now());
            Mockito.when(rs.getObject(DtsColumn.CLOSE_TIME, LocalTime.class)).thenReturn(LocalTime.now());
            Mockito.when(rs.getString(DtsColumn.HOLIDAY)).thenReturn("h");
            Shop shop = shopRowMapper.mapRow(rs, 0);
            return List.of(shop);
        });
        var result = shopRepository.getShopByShopId("TEST_SHP_ID");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("TEST_SHP_ID", result.get().getShopId());
    }

    @Test
    void getShopByShopId_NotAvailable() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenReturn(null);
        var result = shopRepository.getShopByShopId("TEST_SHP_ID");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void getShopList_withAddress() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Shop> shopRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_NAME)).thenReturn("sn");
            Mockito.when(rs.getString(DtsColumn.SHOP_CODE)).thenReturn("sc");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn("TEST_ADD_ID");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn("TEST_ADD_ID");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_LINE_ONE)).thenReturn("al1");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_LINE_TWO)).thenReturn("al2");
            Mockito.when(rs.getString(DtsColumn.CITY)).thenReturn("c");
            Mockito.when(rs.getString(DtsColumn.STATE)).thenReturn("s");
            Mockito.when(rs.getString(DtsColumn.ZIPCODE)).thenReturn("zc");
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("zc");
            Shop shop = shopRowMapper.mapRow(rs, 0);
            return List.of(shop);
        });
        var result = shopRepository.getShopList(0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getShopList_withoutAddress() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Shop> shopRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_NAME)).thenReturn("sn");
            Mockito.when(rs.getString(DtsColumn.SHOP_CODE)).thenReturn("sc");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn(null);
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("zc");
            Shop shop = shopRowMapper.mapRow(rs, 0);
            return List.of(shop);
        });
        var result = shopRepository.getShopList(0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getShopListByUser_withAddress() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Shop> shopRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_NAME)).thenReturn("sn");
            Mockito.when(rs.getString(DtsColumn.SHOP_CODE)).thenReturn("sc");
            Mockito.when(rs.getString(DtsColumn.SHOP_IMAGE)).thenReturn("sc");
            Mockito.when(rs.getString(DtsColumn.SHOP_STATUS)).thenReturn("sc");

            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn("TEST_ADD_ID");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn("TEST_ADD_ID");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_LINE_ONE)).thenReturn("al1");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_LINE_TWO)).thenReturn("al2");
            Mockito.when(rs.getString(DtsColumn.CITY)).thenReturn("c");
            Mockito.when(rs.getString(DtsColumn.STATE)).thenReturn("s");
            Mockito.when(rs.getString(DtsColumn.ZIPCODE)).thenReturn("zc");
            Shop shop = shopRowMapper.mapRow(rs, 0);
            return List.of(shop);
        });
        var result = shopRepository.getShopListByUser("TEST_USR_ID", 0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getShopListByUser_withoutAddress() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Shop> shopRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_NAME)).thenReturn("sn");
            Mockito.when(rs.getString(DtsColumn.SHOP_CODE)).thenReturn("sc");
            Mockito.when(rs.getString(DtsColumn.SHOP_IMAGE)).thenReturn("sc");
            Mockito.when(rs.getString(DtsColumn.SHOP_STATUS)).thenReturn("sc");
            Mockito.when(rs.getString(DtsColumn.ADDRESS_ID)).thenReturn(null);
            Shop shop = shopRowMapper.mapRow(rs, 0);
            return List.of(shop);
        });
        var result = shopRepository.getShopListByUser("TEST_USR_ID", 0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void addShop_withAddress() {
        Shop shop = Shop.builder()
                .shopId("TEST_SHP_ID").shopName("sn").shopCode("sc").createdAt(LocalDateTime.now())
                .shopAddress(Address.builder().addressId("TEST_ADD_ID").build())
                .holiday("h").openTime(LocalTime.now()).closeTime(LocalTime.now()).shopStatus("ss").mobileNo("9090909090")
                .alternateMobileNo("9090909090").shopImage("si")
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalTime.class), Mockito.any(LocalTime.class), Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(1);
        var result = shopRepository.addShop(shop);
        Assertions.assertEquals(1, result);
    }

    @Test
    void addShop_withoutAddress() {
        Shop shop = Shop.builder()
                .shopId("TEST_SHP_ID").shopName("sn").shopCode("sc").createdAt(LocalDateTime.now())
                .holiday("h").openTime(LocalTime.now()).closeTime(LocalTime.now()).shopStatus("ss").mobileNo("9090909090")
                .alternateMobileNo("9090909090").shopImage("si")
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.any(), Mockito.anyString(), Mockito.any(LocalTime.class), Mockito.any(LocalTime.class), Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(1);
        var result = shopRepository.addShop(shop);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateShop() {
        Shop shop = Shop.builder()
                .shopId("TEST_SHP_ID").shopName("sn").shopCode("sc").updatedAt(LocalDateTime.now())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString()))
                .thenReturn(1);
        var result = shopRepository.updateShop(shop);
        Assertions.assertEquals(1, result);
    }

    @Test
    void deleteShopByShopId() {
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(1);
        var result = shopRepository.deleteShopByShopId("TEST_SHP_ID");
        Assertions.assertEquals(1, result);
    }
}