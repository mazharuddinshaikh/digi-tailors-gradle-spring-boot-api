package in.tailor.digi.main.service;

import in.tailor.digi.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public sealed interface UserService permits UserServiceImpl {
    Optional<User> signIn(String userName, String password);

    Optional<User> signup(User user);

    @Transactional
    Optional<User> updateUser(User user);

    @Transactional
    int updatePassword(String userId, String password);
}
