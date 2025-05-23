package in.tailor.digi.main.repository;

import in.tailor.digi.model.User;

import java.util.Optional;

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
