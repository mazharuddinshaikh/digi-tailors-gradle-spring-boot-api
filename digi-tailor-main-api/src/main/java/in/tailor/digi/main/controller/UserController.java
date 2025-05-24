/**
 *
 */
package in.tailor.digi.main.controller;

import in.tailor.digi.main.service.UserService;
import in.tailor.digi.main.service.UserValidationService;
import in.tailor.digi.model.DtsApiResponse;
import in.tailor.digi.model.DtsException;
import in.tailor.digi.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("user")
@Setter
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidationService userValidationService;

    @GetMapping("/test")
    @Operation(summary = "Test If Application is running")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("User Api working");
    }

    @Operation(summary = "Login")
    @PostMapping(value = "v1/signIn")
    public ResponseEntity<User> signIn(@RequestBody User user) throws DtsException {
        return ResponseEntity.ok().body(userService.signIn(user.getUserName(), user.getPassword()).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("Username / Password incorrect")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build())));
    }

    @Operation(summary = "Add user")
    @PostMapping(value = "v1/signup")
    public ResponseEntity<User> signup(@RequestBody User user) throws DtsException {
        final var validationMessage = userValidationService.validateSignupUser(user);
        if (!ObjectUtils.isEmpty(validationMessage)) {
            throw new DtsException(DtsApiResponse.<String>builder().message(validationMessage)
                    .httpStatus(HttpStatus.FORBIDDEN.value()).build());
        }
        return ResponseEntity.ok().body(userService.signup(user).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build())));
    }

    @Operation(summary = "Update user")
    @PutMapping(value = "v1/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) throws DtsException {
        final String validationMessage = userValidationService.validateUpdateUser(user);
        if (!ObjectUtils.isEmpty(validationMessage)) {
            throw new DtsException(DtsApiResponse.<String>builder().message(validationMessage)
                    .httpStatus(HttpStatus.FORBIDDEN.value()).build());
        }
        userService.updateUser(user).orElseThrow(() -> new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build()));
        return ResponseEntity.ok().body("User updated successfully");
    }

    @Operation(summary = "Update password")
    @PutMapping(value = "v1/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody User user) throws DtsException {
        final var validationMessage = userValidationService.validatePassword(user.getPassword());
        if (!ObjectUtils.isEmpty(validationMessage)) {
            throw new DtsException(DtsApiResponse.<String>builder().message(validationMessage)
                    .httpStatus(HttpStatus.NO_CONTENT.value()).build());
        }
        final int passwordUpdated = userService.updatePassword(user.getUserId(), user.getPassword());
        if (passwordUpdated > 0) {
            return ResponseEntity.ok().body("Password updated successfully");
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());
    }
}
