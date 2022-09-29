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
	
	// check positionName if exist in the area
	int getRowCountOfPositionNameInArea(@Param("positionName") String positionName, @Param("positionArea") Integer positionArea);
	
	// get positionId by positionName, positionArea
	Integer getPositionId(@Param("positionArea") Integer positionArea, @Param("positionName") String positionName);
	
	// insert a position
	int addPosition(@Param("position") Position position);
	
	// delete position
	int deletePosition(@Param("positionId") Integer positionId);
	
	// update position
	int updatePosition(@Param("position") Position position);
}
