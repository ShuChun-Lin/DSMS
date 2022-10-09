package div.shuchun.service.user;

import java.util.List;

import div.shuchun.pojo.User;

public interface UserService {
	
	// get login user by userCode (帳號) and userPassword
	User getLoginUser(String userCode, String userPassword);

	// get all user list
	List<User> getAllUserList();
	
	// delete user by id
	boolean deleteUserById(Integer id);
	
	// get user by id
	User getUserById(Integer id);
	
	// get all user list by some conditions
	List<User> getAllUserList(String userName, Integer roleId, Integer deptId);
	
	// update a user
	boolean updateUser(User user);
	
	// insert a new user
	boolean addUser(User user);
	
	// get user by userId
	User getUserByUserId(String userId);
}
