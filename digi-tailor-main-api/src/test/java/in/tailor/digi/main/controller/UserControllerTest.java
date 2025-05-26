package in.tailor.digi.main.controller;

import in.tailor.digi.main.service.UserServiceImpl;
import in.tailor.digi.main.service.UserValidationServiceImpl;
import in.tailor.digi.model.DtsException;
import in.tailor.digi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserServiceImpl userService;
    @Mock
    private UserValidationServiceImpl userValidationService;
    @InjectMocks
    private UserController userController;

    @Test
    public void testApi_ShouldReturnOk() {
        var result = userController.testApi();
        Assertions.assertEquals("User Api working", result.getBody());
    }

    @Test
    void signIn_ShouldReturnUser() throws DtsException {
        User user = new User();
        user.setUserName("TEST_USER");
        user.setPassword("TEST_PASSWORD");
        Mockito.when(userService.signIn(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(user));
        ResponseEntity<User> response = userController.signIn(user);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Mockito.verify(userService).signIn("TEST_USER", "TEST_PASSWORD");
    }

    @Test
    void signIn_ShouldThrowDtsException_WhenFailed() {
        User user = new User();
        user.setUserName("TEST_USER");
        user.setPassword("TEST_PASSWORD");
        Mockito.when(userService.signIn(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                userController.signIn(user)
        );
        Assertions.assertEquals(403, thrown.getResponse().getHttpStatus());
        Mockito.verify(userService).signIn("TEST_USER", "TEST_PASSWORD");
    }

    @Test
    void shouldReturnSuccessResponseWhenSignupSuccessFull() throws DtsException {
        User user = new User();
        // Arrange
        Mockito.when(userValidationService.validateSignupUser(Mockito.any(User.class))).thenReturn(null);
        Mockito.when(userService.signup(Mockito.any(User.class))).thenReturn(Optional.of(user));
        // Act
        ResponseEntity<User> response = userController.signup(user);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldThrowDtsExceptionWhenSignupValidationFailed() {
        User user = new User();
        // Arrange
        Mockito.when(userValidationService.validateSignupUser(Mockito.any(User.class))).thenReturn("Please enter valid user name");
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> userController.signup(user));
        Assertions.assertEquals("Please enter valid user name", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldThrowDtsExceptionWhenSignupFailed() {
        User user = new User();
        // Arrange
        Mockito.when(userValidationService.validateSignupUser(Mockito.any(User.class))).thenReturn(null);
        Mockito.when(userService.signup(Mockito.any(User.class))).thenReturn(Optional.empty());
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> userController.signup(user));
        Assertions.assertEquals("Something went wrong. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldReturnSuccessResponseWhenUserUpdateSuccessFull() throws DtsException {
        User user = new User();
        // Arrange
        Mockito.when(userValidationService.validateUpdateUser(Mockito.any(User.class))).thenReturn(null);
        Mockito.when(userService.updateUser(Mockito.any(User.class))).thenReturn(Optional.of(user));
        // Act
        ResponseEntity<String> response = userController.updateUser(user);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("User updated successfully", response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenUpdateUserValidationFailed() {
        User user = new User();
        // Arrange
        Mockito.when(userValidationService.validateUpdateUser(Mockito.any(User.class))).thenReturn("Please enter valid user name");
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> userController.updateUser(user));
        Assertions.assertEquals("Please enter valid user name", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldThrowDtsExceptionWhenUpdateUserFailed() {
        User user = new User();
        // Arrange
        Mockito.when(userValidationService.validateUpdateUser(Mockito.any(User.class))).thenReturn(null);
        Mockito.when(userService.updateUser(Mockito.any(User.class))).thenReturn(Optional.empty());
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> userController.updateUser(user));
        Assertions.assertEquals("Something went wrong. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldReturnSuccessResponseWhenPasswordUpdateSuccessFull() throws DtsException {
        User user = new User();
        user.setUserId("TEST_USER");
        user.setPassword("TEST_PASSWORD");
        // Arrange
        Mockito.when(userValidationService.validatePassword(Mockito.anyString())).thenReturn(null);
        Mockito.when(userService.updatePassword(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
        // Act
        ResponseEntity<String> response = userController.updatePassword(user);
        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Password updated successfully", response.getBody());
    }

    @Test
    void shouldThrowDtsExceptionWhenUpdatePasswordValidationFailed() {
        User user = new User();
        user.setUserId("TEST_USER");
        user.setPassword("TEST_PASSWORD");
        // Arrange
        Mockito.when(userValidationService.validatePassword(Mockito.anyString())).thenReturn("Please enter valid password. Password is empty.");
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> userController.updatePassword(user));
        Assertions.assertEquals("Please enter valid password. Password is empty.", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }

    @Test
    void shouldThrowDtsExceptionWhenUpdatePasswordFailed() {
        User user = new User();
        user.setUserId("TEST_USER");
        user.setPassword("TEST_PASSWORD");
        // Arrange
        Mockito.when(userValidationService.validatePassword(Mockito.anyString())).thenReturn(null);
        Mockito.when(userService.updatePassword(Mockito.anyString(), Mockito.anyString())).thenReturn(0);
        // Act & Assert
        DtsException exception = Assertions.assertThrows(DtsException.class, () -> userController.updatePassword(user));
        Assertions.assertEquals("Something went wrong. Please retry", exception.getResponse().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), exception.getResponse().getHttpStatus());
    }
}