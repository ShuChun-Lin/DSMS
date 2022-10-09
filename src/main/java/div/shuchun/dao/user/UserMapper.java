package div.shuchun.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.User;

public interface UserMapper {
	
	// get login user by userCode (帳號)
	User getLoginUser(@Param("userCode") String userCode);
	
	// get all user list
	List<User> getAllUserList(@Param("userName") String userName,
			@Param("roleId") Integer roleId,
			@Param("deptId") Integer deptId);
	
	// delete user by id
	Integer deleteUserById(@Param("id") Integer id);
	
	// get user by id
	User getUserById(@Param("id") Integer id);
	
	// update a user
	int updateUser(User user);
	
	// insert a new user
	int addUser(User user);
	
	// get user by userId
	User getUserByUserId(@Param("userId") String userId);
	
}
