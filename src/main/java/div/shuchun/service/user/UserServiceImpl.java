package div.shuchun.service.user;

import java.util.List;

import div.shuchun.dao.user.UserMapper;
import div.shuchun.pojo.User;

public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public List<User> getAllUserList() {
		
		return userMapper.getAllUserList();
	}
	
	
}
