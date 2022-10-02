package div.shuchun.service.area;

import java.util.List;

import div.shuchun.pojo.Area;

public interface AreaService {

	// get areaList
	List<String> getAreaList(Integer deptId, Integer statusId, String partsCode, String positionName);
	
	// is name=?, deptID=? area already exist
	boolean isAreaExist(Integer deptId, String areaName);
	
	// add a new area data to database
	boolean addArea(Area area, Integer deptId);
	
	// delete a area
	int deleteAreaById(Integer id);
	
	// update a area
	boolean updateArea(Area area, Integer deptId);
}
