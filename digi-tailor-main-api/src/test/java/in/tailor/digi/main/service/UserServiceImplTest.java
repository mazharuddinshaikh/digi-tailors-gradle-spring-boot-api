package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.UserRepositoryImpl;
import in.tailor.digi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepositoryImpl userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void signIn() {
        var result = userService.signIn("USR_123", "TEST_PASSWORD");
        Assertions.assertNotNull(result);
    }

    @Test
    void signup_WhenSuccessFull() {
        User user = new User();
        Mockito.when(userRepository.signUp(Mockito.any(User.class))).thenReturn(1);
        var result = userService.signup(user);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void signup_WhenFailed() {
        User user = new User();
        Mockito.when(userRepository.signUp(Mockito.any(User.class))).thenReturn(0);
        var result = userService.signup(user);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void updateUser_WhenSuccessFull() {
        User user = new User();
        Mockito.when(userRepository.updateUser(Mockito.any(User.class))).thenReturn(1);
        var result = userService.updateUser(user);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void updateUser_WhenFailed() {
        User user = new User();
        Mockito.when(userRepository.updateUser(Mockito.any(User.class))).thenReturn(0);
        var result = userService.updateUser(user);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void updatePassword_WhenSuccessFull() {
        Mockito.when(userRepository.updatePassword(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
        var result = userService.updatePassword("TEST_USR_1", "TEST_PASSWORD");
        Assertions.assertEquals(1, result);
    }

    @Test
    void updatePassword_WhenFailed() {
        Mockito.when(userRepository.updatePassword(Mockito.anyString(), Mockito.anyString())).thenReturn(0);
        var result = userService.updatePassword("TEST_USR_1", "TEST_PASSWORD");
        Assertions.assertEquals(0, result);
    }

}