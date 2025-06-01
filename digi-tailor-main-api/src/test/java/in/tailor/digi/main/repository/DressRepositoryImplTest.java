package in.tailor.digi.main.repository;

import in.tailor.digi.model.Customer;
import in.tailor.digi.model.Dress;
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
class DressRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private DressRepositoryImpl dressRepository;

    @Test
    void getDressByUser() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Dress> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.DRESS_ID)).thenReturn("TEST_DRS_ID");
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_USR_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.COMMENT)).thenReturn("comment");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Dress dress = customerRowMapper.mapRow(rs, 0);
            return List.of(dress);
        });
        List<Dress> result = dressRepository.getDressByUser("TEST_USR_ID", 10, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("TEST_DRS_ID", result.get(0).getDressId());
    }

    @Test
    void getDressByShop() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Dress> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.DRESS_ID)).thenReturn("TEST_DRS_ID");
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_USR_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.COMMENT)).thenReturn("comment");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Dress dress = customerRowMapper.mapRow(rs, 0);
            return List.of(dress);
        });
        List<Dress> result = dressRepository.getDressByShop("TEST_SHP_ID", 10, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("TEST_DRS_ID", result.get(0).getDressId());
    }

    @Test
    void getDressByCustomer() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenAnswer(answer -> {
            RowMapper<Dress> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.DRESS_ID)).thenReturn("TEST_DRS_ID");
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_USR_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.COMMENT)).thenReturn("comment");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Dress dress = customerRowMapper.mapRow(rs, 0);
            return List.of(dress);
        });
        List<Dress> result = dressRepository.getDressByCustomer("TEST_CUST_ID", 10, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("TEST_DRS_ID", result.get(0).getDressId());
    }

    @Test
    void getDressByDressId_whenDressPresent() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<Dress> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.DRESS_ID)).thenReturn("TEST_DRS_ID");
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_USR_ID");
            Mockito.when(rs.getString(DtsColumn.SHOP_ID)).thenReturn("TEST_SHP_ID");
            Mockito.when(rs.getString(DtsColumn.CUSTOMER_ID)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.COMMENT)).thenReturn("comment");
            Mockito.when(rs.getObject(DtsColumn.CREATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            Mockito.when(rs.getObject(DtsColumn.UPDATED_AT, LocalDateTime.class)).thenReturn(LocalDateTime.now());
            return customerRowMapper.mapRow(rs, 0);
        });
        var result = dressRepository.getDressByDressId("TEST_DRS_ID");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("TEST_DRS_ID", result.get().getDressId());
    }

    @Test
    void getDressByDressId_whenDressNotPresent() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenThrow(new EmptyResultDataAccessException(0));
        var result = dressRepository.getDressByDressId("TEST_DRS_ID");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void addDress_withShopAndCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .shop(Shop.builder().shopId("TEST_SHP_ID").build())
                .customer(Customer.builder().customerId("TEST_CUST_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(1);
        var result = dressRepository.addDress(dress);
        Assertions.assertEquals(1, result);
    }

    @Test
    void addDress_withoutShopAndWithoutCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.anyString(),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(1);
        var result = dressRepository.addDress(dress);
        Assertions.assertEquals(1, result);
    }

    @Test
    void addDress_withShopAndWithoutCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .shop(Shop.builder().shopId("TEST_SHP_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.anyString(),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(1);
        var result = dressRepository.addDress(dress);
        Assertions.assertEquals(1, result);
    }

    @Test
    void addDress_withoutShopAndWithCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .customer(Customer.builder().customerId("TEST_CUST_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(1);
        var result = dressRepository.addDress(dress);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateDress_withShopAndCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .shop(Shop.builder().shopId("TEST_SHP_ID").build())
                .customer(Customer.builder().customerId("TEST_CUST_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString()))
                .thenReturn(1);
        var result = dressRepository.updateDress(dress);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateDress_withoutShopAndWithoutCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.any(), Mockito.any(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString()))
                .thenReturn(1);
        var result = dressRepository.updateDress(dress);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateDress_withShopAndWithoutCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .shop(Shop.builder().shopId("TEST_SHP_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString()))
                .thenReturn(1);
        var result = dressRepository.updateDress(dress);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateDress_withoutShopAndWithCustomer() {
        var dress = Dress.builder()
                .dressId("TEST_DRS_ID")
                .comment("comment")
                .createdAt(LocalDateTime.now())
                .user(User.builder().userId("TEST_USR_ID").build())
                .customer(Customer.builder().customerId("TEST_CUST_ID").build())
                .build();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(),
                        Mockito.any(), Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString()))
                .thenReturn(1);
        var result = dressRepository.updateDress(dress);
        Assertions.assertEquals(1, result);
    }

}