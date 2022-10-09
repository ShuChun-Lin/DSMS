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
		return getAllUserList(null,null,null);
	}


	@Override
	public boolean deleteUserById(Integer id) {
		int result = userMapper.deleteUserById(id);
		if (result == 1) {
			return true;
		}
		return false;
	}


	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}


	@Override
	public List<User> getAllUserList(String userName, Integer roleId, Integer deptId) {
		return userMapper.getAllUserList(userName, roleId, deptId);
	}


	@Override
	public boolean updateUser(User user) {
		if (userMapper.updateUser(user) == 1) {
			return true;
		}
		return false;
	}


	@Override
	public boolean addUser(User user) {
		if (userMapper.addUser(user) == 1) {
			return true;
		}
		return false;
	}


	@Override
	public User getUserByUserId(String userId) {
		return userMapper.getUserByUserId(userId);
	}

}
