package in.tailor.digi.main.repository;

import in.tailor.digi.model.UserShopMapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class UserShopMappingRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private UserShopMappingRepositoryImpl userShopMappingRepositoryImpl;

    @Test
    void addUserShopMapping() {
        UserShopMapping userShopMapping = UserShopMapping.builder().userId("TEST_USR_ID").shopId("TEST_SHP_ID")
                .userType("ut").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(1);
        var result = userShopMappingRepositoryImpl.addUserShopMapping(userShopMapping);
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateUserShopMapping() {
        UserShopMapping userShopMapping = UserShopMapping.builder().userId("TEST_USR_ID").shopId("TEST_SHP_ID")
                .userType("ut").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.any(LocalDateTime.class),
                        Mockito.anyString(), Mockito.anyString()))
                .thenReturn(1);
        var result = userShopMappingRepositoryImpl.updateUserShopMapping(userShopMapping);
        Assertions.assertEquals(1, result);
    }

    @Test
    void deleteUserShopMapping() {
        UserShopMapping userShopMapping = UserShopMapping.builder().userId("TEST_USR_ID").shopId("TEST_SHP_ID")
                .userType("ut").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(1);
        var result = userShopMappingRepositoryImpl.deleteUserShopMapping(userShopMapping);
        Assertions.assertEquals(1, result);
    }
}