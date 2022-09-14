package div.shuchun.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Position;


public interface PositionMapper {

	// get position by part id , status id and department id
	List<Position> getPositionName(@Param("partsId") int partsId, @Param("statusId") int statusId, @Param("deptId") int deptId);
}
