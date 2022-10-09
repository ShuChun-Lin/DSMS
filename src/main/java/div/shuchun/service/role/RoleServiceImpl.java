package div.shuchun.service.role;

import java.util.List;

import div.shuchun.dao.role.RoleMapper;
import div.shuchun.pojo.Role;

public class RoleServiceImpl implements RoleService{
	
	private RoleMapper roleMapper;
	
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	
	@Override
	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}

	
}
