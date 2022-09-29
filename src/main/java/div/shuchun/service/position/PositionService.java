package div.shuchun.service.position;

import java.util.List;

import div.shuchun.pojo.Position;
import div.shuchun.pojo.User;

public interface PositionService {

	// get position string by partsId, statusId, deptId
	List<String> getPosition(String partsCode, int statusId, User user);
	
	// get searchAreaPosition
	List<String> getSearchAreaPosition(Integer positionArea, Integer statusId, String partsCode, String positionName);
	
	// add a new position
	boolean addPosition(Position position);
	
	// delete a position
	boolean deletePosition(String positionName, String positionArea);
	
	// get position id
	Integer getPositionId(Integer positionArea, String positionName);
	
	// update position
	boolean updatePosition(Position position);
}
