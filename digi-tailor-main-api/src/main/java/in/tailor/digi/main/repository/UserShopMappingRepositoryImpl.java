package in.tailor.digi.main.repository;

import in.tailor.digi.model.UserShopMapping;
import in.tailor.digi.utils.DtsDateTimeUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Setter
public non-sealed class UserShopMappingRepositoryImpl implements UserShopMappingRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addUserShopMapping(UserShopMapping userShopMapping) {
        return jdbcTemplate.update(
                "INSERT INTO DTS_USER_SHOP_MAPPING ( USER_ID, SHOP_ID, USER_TYPE, CREATED_AT) VALUES (?, ?, ?, ?)",
                userShopMapping.getUserId(), userShopMapping.getShopId(), userShopMapping.getUserType(),
                DtsDateTimeUtil.getIndianCurrentDateTime());
    }

    @Override
    public int updateUserShopMapping(UserShopMapping userShopMapping) {
        return jdbcTemplate.update(
                "UPDATE DTS_USER_SHOP_MAPPING SET USER_TYPE = ?, UPDATED_AT = ? WHERE SHOP_ID = ? AND USER_ID = ?",
                userShopMapping.getUserType(), DtsDateTimeUtil.getIndianCurrentDateTime(), userShopMapping.getShopId(),
                userShopMapping.getUserId());
    }

    @Override
    public int deleteUserShopMapping(UserShopMapping userShopMapping) {
        return jdbcTemplate.update("DELETE FROM DTS_USER_SHOP_MAPPING WHERE SHOP_ID = ? AND USER_ID = ?",
                userShopMapping.getShopId(), userShopMapping.getUserId());
    }
}
