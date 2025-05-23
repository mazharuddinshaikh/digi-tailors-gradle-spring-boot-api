package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.UserRepository;
import in.tailor.digi.model.User;
import in.tailor.digi.utils.DtsCodeUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Setter
public non-sealed class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> signIn(String userName, String password) {
        return userRepository.signIn(userName, password);
    }

    @Override
    @Transactional
    public Optional<User> signup(User user) {
        user.setUserId(DtsCodeUtils.generateUserId());
        if (userRepository.signUp(user) > 0)
            return Optional.of(user);
        else
            return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<User> updateUser(User user) {
        if (userRepository.updateUser(user) > 0)
            return Optional.ofNullable(user);
        else
            return Optional.empty();
    }

    @Transactional
    @Override
    public int updatePassword(String userId, String password) {
        return userRepository.updatePassword(userId, password);
    }
}