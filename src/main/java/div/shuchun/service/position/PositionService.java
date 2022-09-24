package div.shuchun.service.position;

import java.util.List;

import div.shuchun.pojo.User;

public interface PositionService {

	// get position string by partsId, statusId, deptId
	List<String> getPosition(String partsCode, int statusId, User user);
	
	// get searchAreaPosition
	List<String> getSearchAreaPosition(Integer positionArea, Integer statusId, String partsCode, String positionName);
	

}
