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
class UserValidationServiceImplTest {
    @Mock
    private UserRepositoryImpl userRepository;
    @InjectMocks
    private UserValidationServiceImpl UserValidationService;

    @Test
    void validateSignupUser_WhenUserNameIsEmpty() {
        User user = new User();
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter valid user name. User name must be more than 5 character.", result);
    }

    @Test
    void validateSignupUser_WhenUserNameLengthIsLess() {
        User user = new User();
        user.setUserName("TEM");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter valid user name. User name must be more than 5 character.", result);
    }

    @Test
    void validateSignupUser_WhenUserNameAlreadyExist() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        Mockito.when(userRepository.isUserNameExist(Mockito.anyString())).thenReturn(true);
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter different user name. User name already exist.", result);
    }

    @Test
    void validateSignupUser_WhenSuccessFull() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        Mockito.when(userRepository.isUserNameExist(Mockito.anyString())).thenReturn(false);
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertNull(result);
    }

    @Test
    void validateSignupUser_WhenPasswordIsEmpty() {
        User user = new User();
        user.setUserName("VALID_USER_NAME");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter valid password. Password must be more than 5 character.", result);
    }

    @Test
    void validateSignupUser_WhenPasswordLengthIsLess() {
        User user = new User();
        user.setUserName("VALID_USER_NAME");
        user.setPassword("PASS");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter valid password. Password must be more than 5 character.", result);
    }

    @Test
    void validateSignupUser_WhenPasswordIsValid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertNull(result);
    }

    @Test
    void validateSignupUser_WhenMobileNoIsEmpty() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertNull(result);
    }

    @Test
    void validateSignupUser_WhenMobileNoIsInvalid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setMobileNo("923");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter valid mobile number.", result);
    }

    @Test
    void validateSignupUser_WhenMobileNoAlreadyExist() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setMobileNo("9090909090");
        Mockito.when(userRepository.isMobileNoExist(Mockito.anyString())).thenReturn(true);
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter different mobile number. Mobile number already exist.", result);
    }

    @Test
    void validateSignupUser_WhenMobileNoIsValid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setMobileNo("9090909090");
        Mockito.when(userRepository.isMobileNoExist(Mockito.anyString())).thenReturn(false);
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertNull(result);
    }

    @Test
    void validateSignupUser_WhenEmailIsEmpty() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertNull(result);
    }

    @Test
    void validateSignupUser_WhenEmailIsInvalid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setEmail("@123");
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter valid email id.", result);
    }

    @Test
    void validateSignupUser_WhenEmailAlreadyExist() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setEmail("xyz@gmail.com");
        Mockito.when(userRepository.isEmailExist(Mockito.anyString())).thenReturn(true);
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertEquals("Please enter different email. Email id already exist.", result);
    }

    @Test
    void validateSignupUser_WhenEmailIsValid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setEmail("xyz@gmail.com");
        Mockito.when(userRepository.isEmailExist(Mockito.anyString())).thenReturn(false);
        var result = UserValidationService.validateSignupUser(user);
        Assertions.assertNull(result);
    }

    @Test
    void validateUpdateUser_WhenMobileNoInvalid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setMobileNo("9090");
        var result = UserValidationService.validateUpdateUser(user);
        Assertions.assertEquals("Please enter valid mobile number.", result);
    }

    @Test
    void validateUpdateUser_WhenEmailInvalid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setMobileNo("9090909090");
        user.setEmail("@123");
        var result = UserValidationService.validateUpdateUser(user);
        Assertions.assertEquals("Please enter valid email id.", result);
    }

    @Test
    void validateUpdateUser_WhenMobileNoAndEmailValid() {
        User user = new User();
        user.setUserName("TEST_USER_NAME");
        user.setPassword("VALID_PASSWORD");
        user.setMobileNo("9090909090");
        user.setEmail("xyz@123.com");
        var result = UserValidationService.validateUpdateUser(user);
        Assertions.assertNull(result);
    }


}