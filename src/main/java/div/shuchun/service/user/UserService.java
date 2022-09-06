package div.shuchun.service.user;

import java.util.List;

import div.shuchun.pojo.User;

public interface UserService {
	
	// get login user by userCode (帳號) and userPassword
	User getLoginUser(String userCode, String userPassword);

	// get all user list
	List<User> getAllUserList();
	
}
