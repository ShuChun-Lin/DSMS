package div.shuchun.service.position;

import java.util.ArrayList;
import java.util.List;

import div.shuchun.dao.parts.PartsMapper;
import div.shuchun.dao.position.PositionMapper;
import div.shuchun.pojo.Position;
import div.shuchun.pojo.SearchAreaPositionObj;
import div.shuchun.pojo.User;

public class PositionServiceImpl implements PositionService {
	
	private PositionMapper positionMapper;
	private PartsMapper partsMapper;

	public void setPositionMapper(PositionMapper positionMapper) {
		this.positionMapper = positionMapper;
	}
	
	public void setPartsMapper(PartsMapper partsMapper) {
		this.partsMapper = partsMapper;
	}

	
	@Override
	public List<String> getPosition(String partsCode, int statusId, User user) {
		
		List<String> positionString = new ArrayList<>();
		
		if (user == null) {
			positionString.add("{\"warning\":\"登入時間超時，請重新登入\"}");
			return positionString;
		}
		int deptId = user.getUserDepartment();
		
		Integer partsId = partsMapper.getPartsIdByDept(partsCode, deptId);
		if (partsId == null || partsId < 1) {
			positionString.add("{\"warning\":\"沒有找到該 part code (請檢查部門物料資訊)\"}");
			return positionString;
		}
		
		List<Position> positionList = positionMapper.getPositionName(partsId, statusId, deptId);
		if (positionList.size() == 0) {
			positionString.add("{\"warning\":\"沒有找到儲位 (請檢查部門儲區 & 儲位資訊)\"}");
			return positionString;
		}
		
		for (Position position : positionList) {
			positionString.add(position.toStringAsJson());
		}
		return positionString;
	}

	@Override
	public List<String> getSearchAreaPosition(Integer positionArea, Integer statusId, String partsCode,
			String positionName) {
		List<String> searchAreaPositionJsonList = new ArrayList<>();
		
		List<SearchAreaPositionObj> searchAreaPositionList = positionMapper.getSearchAreaPosition(positionArea, 
				statusId, partsCode, positionName);
		
		if (searchAreaPositionList.size() == 0) {
			searchAreaPositionJsonList.add("{\"warning\":\"沒有儲位\"}");
			return searchAreaPositionJsonList;
		}
		
		
		for (SearchAreaPositionObj sapObj : searchAreaPositionList) {
			searchAreaPositionJsonList.add(sapObj.toStringAsJson());
		}
		return searchAreaPositionJsonList;
	}

	@Override
	public boolean addPosition(Position position) {
		// check positionName if exist in the area
		if (positionMapper.getRowCountOfPositionNameInArea(position.getPositionName(), position.getPositionArea()) > 0) {
			System.out.println("positionName already exist! can not create this positionName again");
			return false;
		}
		if (positionMapper.addPosition(position) != 1) {
			System.out.println("add position name: " + position.getPositionName() + " -> fail");
			return false;
		}
		System.out.println("add position name: " + position.getPositionName() + " -> success");
		return true;
	}

	@Override
	public boolean deletePosition(String positionName, String positionArea) {
		// get position id
		Integer positionId = getPositionId(Integer.parseInt(positionArea), positionName);
		if (positionId == null || positionId < 1) {
			System.out.println("刪除失敗，找不到該儲位ID");
			return false;
		}
		
		// check partsInst table is exist this position id (if it exists) the position can not be delete
		if (partsMapper.getRowCountOfPartsInstByPositionId(positionId) > 0) {
			System.out.println("刪除失敗，儲位ID使用中");
			return false;
		}
		
		// delete position
		if (positionMapper.deletePosition(positionId) != 1) {
			System.out.println("刪除失敗，刪除異常");
			return false;
		}
		return true;
	}

	@Override
	public Integer getPositionId(Integer positionArea, String positionName) {
		return positionMapper.getPositionId(positionArea, positionName);
	}

	@Override
	public boolean updatePosition(Position position) {
		if (positionMapper.updatePosition(position) != 1) {
			System.out.println("修改失敗，修改異常");
			return false;
		}
		return true;
	}

}
