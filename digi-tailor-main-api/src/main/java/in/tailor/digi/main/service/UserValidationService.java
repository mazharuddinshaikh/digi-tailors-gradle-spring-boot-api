package in.tailor.digi.main.service;

import in.tailor.digi.model.User;

public sealed interface UserValidationService permits UserValidationServiceImpl {
    String validateSignupUser(User user);
    String validateUpdateUser(User user);
    String validatePassword(String password);
}
