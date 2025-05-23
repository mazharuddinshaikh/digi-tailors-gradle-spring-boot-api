package in.tailor.digi.user.service;

import java.util.Optional;

import in.tailor.digi.model.User;
import org.springframework.transaction.annotation.Transactional;

public sealed interface UserService permits UserServiceImpl {
	Optional<User> signIn(String userName, String password);
	Optional<User> signup(User user);

	@Transactional
	Optional<User> updateUser(User user);

	@Transactional
	int updatePassword(String userId, String password);
}
