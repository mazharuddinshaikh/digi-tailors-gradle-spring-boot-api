package in.tailor.digi.user.repository;

import java.util.Optional;

import in.tailor.digi.model.User;

public sealed interface UserRepository permits UserRepositoryImpl{
	
	Optional<User> signIn(String userName, String password);
	boolean isUserNameExist(String userName);

//	boolean isEmailExist(String email, String userName);
//
//	boolean isMobileNoExist(String mobileNo, String userName);

	boolean isEmailExist(String email);
	boolean isMobileNoExist(String mobileNo);
	int signUp(User user);
	int updateUser(User user);
	int updatePassword(String userId, String password);
}
