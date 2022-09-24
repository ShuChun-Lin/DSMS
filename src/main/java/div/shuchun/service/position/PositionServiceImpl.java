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
		List<SearchAreaPositionObj> searchAreaPositionList = positionMapper.getSearchAreaPosition(positionArea, 
				statusId, partsCode, positionName);
		
		List<String> searchAreaPositionJsonList = new ArrayList<>();
		for (SearchAreaPositionObj sapObj : searchAreaPositionList) {
			searchAreaPositionJsonList.add(sapObj.toStringAsJson());
		}
		return searchAreaPositionJsonList;
	}

}
