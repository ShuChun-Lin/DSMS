package div.shuchun.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.User;

public interface UserMapper {
	
	// get login user by userCode (帳號)
	User getLoginUser(@Param("userCode") String userCode);
	
	// get all user list
	List<User> getAllUserList();
	
}
