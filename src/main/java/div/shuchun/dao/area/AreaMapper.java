package div.shuchun.dao.area;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Area;

public interface AreaMapper {

	// get areaList
	List<Area> getAreaList(@Param("deptId") Integer deptId);
	
}
