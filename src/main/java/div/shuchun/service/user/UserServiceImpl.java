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
	public User getLoginUser(String userCode, String userPassword) {
		User user = userMapper.getLoginUser(userCode);
		
		if (user != null) {
			// judge password whether correct
			if (user.getUserPassword().equals(userPassword)) {
				return user;
			}
		}
		
		return null;
	}

	@Override
	public List<User> getAllUserList() {
		
		return userMapper.getAllUserList();
	}

}
