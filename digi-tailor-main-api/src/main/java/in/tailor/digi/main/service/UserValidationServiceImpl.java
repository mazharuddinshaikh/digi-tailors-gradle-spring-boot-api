package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.UserRepository;
import in.tailor.digi.model.User;
import in.tailor.digi.utils.DtsValidationUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.function.Function;

@Service
@Setter
public non-sealed class UserValidationServiceImpl implements UserValidationService {
    @Autowired
    private UserRepository userRepository;

    private Function<String, String> userNameValidator = userName -> {
        if (ObjectUtils.isEmpty(userName) || userName.length() < 5) {
            return "Please enter valid user name. User name must be more than 5 character.";
        }
        if (userRepository.isUserNameExist(userName)) {
            return "Please enter different user name. User name already exist.";
        }
        return null;
    };

    private Function<String, String> passwordValidator = password -> {
        if (ObjectUtils.isEmpty(password) || password.length() < 5) {
            return "Please enter valid password. Password must be more than 5 character.";
        }
        return null;
    };

    private Function<String, String> mobileNoValidator = mobileNumber -> {
        if (ObjectUtils.isEmpty(mobileNumber)) {
            return null;
        }
        if (!DtsValidationUtils.isValidMobileNo(mobileNumber)) {
            return "Please enter valid mobile number.";
        }
        if (userRepository.isMobileNoExist(mobileNumber)) {
            return "Please enter different mobile number. Mobile number already exist.";
        }
        return null;
    };

    private Function<String, String> emailValidator = email -> {
        if (ObjectUtils.isEmpty(email)) {
            return null;
        }
        if (!DtsValidationUtils.isValidEmail(email)) {
            return "Please enter valid email id.";
        }
        if (userRepository.isEmailExist(email)) {
            return "Please enter different email. Email id already exist.";
        }
        return null;
    };

    @Override
    public String validateSignupUser(User user) {
        String validationMessage = validateUserName(user.getUserName());
        if (!ObjectUtils.isEmpty(validationMessage)) {
            return validationMessage;
        }
        validationMessage = validatePassword(user.getPassword());
        if (!ObjectUtils.isEmpty(validationMessage)) {
            return validationMessage;
        }
        validationMessage = validateMobileNo(user.getMobileNo());
        if (!ObjectUtils.isEmpty(validationMessage)) {
            return validationMessage;
        }
        validationMessage = validateEmail(user.getEmail());
        if (!ObjectUtils.isEmpty(validationMessage)) {
            return validationMessage;
        }
        return null;
    }

    @Override
    public String validateUpdateUser(User user) {
        String validationMessage = validateMobileNo(user.getMobileNo());
        if (!ObjectUtils.isEmpty(validationMessage)) {
            return validationMessage;
        }
        validationMessage = validateEmail(user.getEmail());
        if (!ObjectUtils.isEmpty(validationMessage)) {
            return validationMessage;
        }
        return null;
    }

    public String validatePassword(String password) {
        return passwordValidator.apply(password);
    }

    private String validateUserName(String userName) {
        return userNameValidator.apply(userName);
    }

    private String validateMobileNo(String mobileNumber) {
        return mobileNoValidator.apply(mobileNumber);
    }

    private String validateEmail(String email) {
        return emailValidator.apply(email);
    }
}
