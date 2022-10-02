package div.shuchun.dao.area;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Area;

public interface AreaMapper {

	// get areaList
	List<Area> getAreaList(@Param("deptId") Integer deptId,
			@Param("statusId") Integer statusId,
			@Param("partsCode") String partsCode,
			@Param("positionName") String positionName);
	
	// get area by areaName & areaDepartment
	List<Area> getAreaByNameAndDeptId (@Param("deptId") Integer deptId,
			@Param("areaName") String areaName);
	
	// insert a new area
	int addArea(Area area);
	
	// delete a area
	int deleteAreaById(@Param("id")Integer id);
	
	// update a area
	int updateArea(Area area);
	
	// get areaName by areaId
	String getAreaNameById(@Param("id") Integer id);
	
}