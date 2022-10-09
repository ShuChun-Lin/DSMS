package div.shuchun.service.dept;

import java.util.List;

import div.shuchun.dao.dept.DepartmentMapper;
import div.shuchun.pojo.Department;

public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentMapper departmentMapper;
	
	public void setDepartmentMapper(DepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}
	
	@Override
	public List<Department> getDeptList() {
		return departmentMapper.getDeptList();
	}

}
