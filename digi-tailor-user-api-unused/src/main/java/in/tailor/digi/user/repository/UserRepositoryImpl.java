package in.tailor.digi.user.repository;

import in.tailor.digi.model.User;
import in.tailor.digi.model.table.DtsColumn;
import in.tailor.digi.utils.DtsUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Setter
public non-sealed class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> signIn(String userName, String password) {
        User user;
        final String query = "SELECT DTS_USER.USER_ID, DTS_USER.FIRST_NAME, DTS_USER.MIDDLE_NAME, DTS_USER.LAST_NAME, "
                + "DTS_USER.MOBILE_NO, DTS_USER.EMAIL, DTS_USER.USER_NAME, DTS_USER.ALTERNATE_MOBILE_NO  FROM DTS_USER DTS_USER "
                + "WHERE (DTS_USER.USER_NAME = ? OR DTS_USER.MOBILE_NO = ? OR DTS_USER.EMAIL = ?) AND DTS_USER.PASSWORD = ?";
        try {
            user = jdbcTemplate.queryForObject(query, (rs, rowNum) -> User.builder().userId(rs.getString(DtsColumn.USER_ID))
                    .firstName(rs.getString(DtsColumn.FIRST_NAME)).middleName(rs.getString(DtsColumn.MIDDLE_NAME))
                    .lastName(rs.getString(DtsColumn.LAST_NAME)).userName(rs.getString(DtsColumn.USER_NAME))
                    .mobileNo(rs.getString(DtsColumn.MOBILE_NO))
                    .alternateMobileNo(rs.getString(DtsColumn.ALTERNATE_MOBILE_NO))
                    .email(rs.getString(DtsColumn.EMAIL)).build(), userName, userName, userName, password);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean isUserNameExist(String userName) {
        Integer count;
        final String query = "SELECT COUNT(USER_NAME) AS COUNT  FROM DTS_USER DTS_USER WHERE DTS_USER.USER_NAME = ?";
        try {
            count = jdbcTemplate.queryForObject(query, (rs, rowNum) -> rs.getInt("COUNT"), userName);
        } catch (EmptyResultDataAccessException exception) {
            return false;
        }
        return count != null && count > 0;
    }

    @Override
    public boolean isEmailExist(String email) {
        return getUser("EMAIL", email).isPresent();
    }

    @Override
    public boolean isMobileNoExist(String mobileNo) {
        return getUser("MOBILE_NO", mobileNo).isPresent();
    }

    @Override
    public int signUp(User user) {
        final String query = "INSERT INTO DTS_USER(USER_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, MOBILE_NO, EMAIL, USER_NAME, " +
                "PASSWORD, ALTERNATE_MOBILE_NO, CREATED_AT) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, user.getUserId(), user.getFirstName(), user.getMiddleName(),
                user.getLastName(), user.getMobileNo(), user.getEmail(), user.getUserName(), user.getPassword(),
                user.getAlternateMobileNo(), DtsUtils.getIndianCurrentDateTime());
    }

    @Override
    public int updateUser(User user) {
        final String query = "UPDATE DTS_USER SET FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, MOBILE_NO = ?, EMAIL = ?, " +
                "ALTERNATE_MOBILE_NO = ?, UPDATED_AT = ? WHERE USER_ID = ?";
        return jdbcTemplate.update(query, user.getFirstName(), user.getMiddleName(),
                user.getLastName(), user.getMobileNo(), user.getEmail(),
                user.getAlternateMobileNo(), DtsUtils.getIndianCurrentDateTime(), user.getUserId());

    }

    @Override
    public int updatePassword(String userId, String password) {
        return jdbcTemplate.update("UPDATE DTS_USER SET PASSWORD = ? WHERE USER_ID = ?", password, userId);
    }

    private Optional<User> getUser(String key, String value) {
        List<User> userList;
        String query = "SELECT DTS_USER.USER_ID  FROM DTS_USER DTS_USER ";
        if (key.equals("EMAIL")) {
            query += "WHERE DTS_USER.EMAIL = ?";
        } else {
            query += "WHERE DTS_USER.MOBILE_NO = ?";
        }
        userList = jdbcTemplate.query(query, (rs, rowNum) ->
                User.builder().userId(rs.getString(DtsColumn.USER_ID)).build(), value);
        if (!CollectionUtils.isEmpty(userList)) {
            return Optional.of(userList.get(0));
        }
        return Optional.empty();
    }
}
