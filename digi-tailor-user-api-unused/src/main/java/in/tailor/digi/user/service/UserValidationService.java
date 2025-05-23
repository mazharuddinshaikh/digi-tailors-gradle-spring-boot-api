package in.tailor.digi.user.service;

import in.tailor.digi.model.User;

public sealed interface UserValidationService permits UserValidationServiceImpl {
    String validateSignupUser(User user);
    String validateUpdateUser(User user);
    String validatePassword(String password);
}
