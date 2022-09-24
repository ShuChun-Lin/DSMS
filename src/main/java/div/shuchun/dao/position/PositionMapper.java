package div.shuchun.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Position;
import div.shuchun.pojo.SearchAreaPositionObj;


public interface PositionMapper {

	// get position by part id , status id and department id
	List<Position> getPositionName(@Param("partsId") int partsId, @Param("statusId") int statusId, @Param("deptId") int deptId);
	
	// get searchAreaPosition
	List<SearchAreaPositionObj> getSearchAreaPosition(@Param("positionArea") Integer positionArea,
			@Param("statusId") Integer statusId,
			@Param("partsCode") String partsCode,
			@Param("positionName") String positionName);
	
}
