package in.tailor.digi.main.repository;

import in.tailor.digi.model.Customer;
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
class UserRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    void signIn_whenSuccessFull() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<Customer> customerRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TEST_USR_ID");
            Mockito.when(rs.getString(DtsColumn.FIRST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MIDDLE_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.LAST_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.USER_NAME)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.MOBILE_NO)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.ALTERNATE_MOBILE_NO)).thenReturn("TEST_CUST_ID");
            Mockito.when(rs.getString(DtsColumn.EMAIL)).thenReturn("TEST_CUST_ID");
            return customerRowMapper.mapRow(rs, 0);
        });
        var result = userRepositoryImpl.signIn("testUserName", "password");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("TEST_USR_ID", result.get().getUserId());
    }

    @Test
    void signIn_whenSuccessFailed() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString()
        )).thenThrow(new EmptyResultDataAccessException(0));
        var result = userRepositoryImpl.signIn("testUserName", "password");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void isUserNameExist_whenUserNameAvailable() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<Integer> countRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getInt("COUNT")).thenReturn(1);
            return countRowMapper.mapRow(rs, 0);
        });
        var result = userRepositoryImpl.isUserNameExist("testUserName");
        Assertions.assertTrue(result);
    }

    @Test
    void isUserNameExist_whenUserNameNotAvailable() {
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenThrow(new EmptyResultDataAccessException(0));
        var result = userRepositoryImpl.isUserNameExist("testUserName");
        Assertions.assertFalse(result);
    }

    @Test
    void isEmailExist_whenEmailAvailable() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<User> countRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TST_USR_ID");
            return List.of(countRowMapper.mapRow(rs, 0));
        });
        var result = userRepositoryImpl.isEmailExist("xyz@email.com");
        Assertions.assertTrue(result);
    }

    @Test
    void isEmailExist_whenEmailNotAvailable() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenReturn(null);
        var result = userRepositoryImpl.isEmailExist("xyz@email.com");
        Assertions.assertFalse(result);
    }

    @Test
    void isMobileNoExist_whenAvailable() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenAnswer(answer -> {
            RowMapper<User> countRowMapper = answer.getArgument(1);
            ResultSet rs = Mockito.mock(ResultSet.class);
            Mockito.when(rs.getString(DtsColumn.USER_ID)).thenReturn("TST_USR_ID");
            return List.of(countRowMapper.mapRow(rs, 0));
        });
        var result = userRepositoryImpl.isMobileNoExist("9999999999");
        Assertions.assertTrue(result);
    }

    @Test
    void isMobileNoExist_whenNotAvailable() {
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(RowMapper.class),
                Mockito.anyString()
        )).thenReturn(null);
        var result = userRepositoryImpl.isMobileNoExist("9999999999");
        Assertions.assertFalse(result);
    }

    @Test
    void signup() {
        User user = User.builder().userId("TEST_USR_ID").firstName("fn").middleName("mn").lastName("ln").mobileNo("9999999999")
                .email("xyz@email.com").userName("TEST_USR_1").password("password").alternateMobileNo("9999999999")
                .createdAt(LocalDateTime.now()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalDateTime.class)))
                .thenReturn(1);
        var result = userRepositoryImpl.signUp(user);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateUser() {
        User user = User.builder().userId("TEST_USR_ID").firstName("fn").middleName("mn").lastName("ln").mobileNo("9999999999")
                .email("xyz@email.com").userName("TEST_USR_1").password("password").alternateMobileNo("9999999999")
                .updatedAt(LocalDateTime.now()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.any(LocalDateTime.class), Mockito.anyString()))
                .thenReturn(1);
        var result = userRepositoryImpl.updateUser(user);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updatePassword() {
        User user = User.builder().userId("TEST_USR_ID").firstName("fn").middleName("mn").lastName("ln").mobileNo("9999999999")
                .email("xyz@email.com").userName("TEST_USR_1").password("password").alternateMobileNo("9999999999")
                .updatedAt(LocalDateTime.now()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(1);
        var result = userRepositoryImpl.updatePassword("TEST_USR_ID", "password");
        Assertions.assertEquals(1, result);
    }
}