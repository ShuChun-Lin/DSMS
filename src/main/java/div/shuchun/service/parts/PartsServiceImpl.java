package div.shuchun.service.parts;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import div.shuchun.dao.parts.PartsMapper;
import div.shuchun.dao.position.PositionMapper;
import div.shuchun.pojo.ImportObj;
import div.shuchun.pojo.Parts;
import div.shuchun.pojo.Position;
import div.shuchun.utils.PageSupport;

public class PartsServiceImpl implements PartsService {

	private PositionMapper positionMapper;
	private PartsMapper partsMapper;

	public void setPartsMapper(PartsMapper partsMapper) {
		this.partsMapper = partsMapper;
	}

	public void setPositionMapper(PositionMapper positionMapper) {
		this.positionMapper = positionMapper;
	}

	@Override
	public List<Parts> getPartsListByCode(String partsCode, int pageIndex, int pageSize) {
		
		// calculate item start index by pageIndex and pageSize
		int startIndex = pageSize * (pageIndex - 1);
		List<Parts> partsList = partsMapper.getPartsListByCode(partsCode, startIndex, pageSize);
		
		// get every counts of part
		for(Parts part : partsList) {
			part.setPartsCount(getPartsCountById(part.getId()));
		}
		return partsList;
	}

	@Override
	public int getPartsCountById(int id) {
		List<Integer> countList = partsMapper.getPartsCountById(id);
		int sum = 0;
		for (Integer count : countList) {
			sum += count;
		}
		return sum;
	}

	@Override
	public int getPartsDataCount(String partsCode) {
		return partsMapper.getPartsDataCount(partsCode);
	}

	@Override
	public PageSupport getPageSupportImpl(String pageIndex, int totalCount) {
		PageSupport pageSupport =  new PageSupport();
		
		// calculate page
		pageSupport.pageCalculation(pageIndex, totalCount);
		
		return pageSupport;
	}

	@Override
	public boolean importParts(String jsonArrayString, int deptId) {
		JSONArray jsonArray = JSONObject.parseArray(jsonArrayString);
		// Json Array transfer to List<ImportObj>
		List<ImportObj> importObjList = new ArrayList<>();
		JSONObject jObj;
		ImportObj iObj;
		for (int i=0; i<jsonArray.size(); i++) {
			jObj = (JSONObject) jsonArray.get(i);
			iObj = new ImportObj(jObj.getInteger("statusId"), 
					jObj.getString("partsCode"), 
					jObj.getInteger("quantity"), 
					jObj.getString("position")
			);
			importObjList.add(iObj);
		}
		// 得到了前端傳入的 statusId partsCode quantity positionName
		// 這邊先對一個 import object 做分析
		// 1. 取得 parts id (正常不會有錯，前端輸入時應該檢查過了)
		Integer partsId = partsMapper.getPartsIdByDept(importObjList.get(0).getPartsCode(), deptId);
		if (partsId == null || partsId < 1) {
			return false;
		}
		// 2. 取得 position id
		//		先拿到所有對應的 position id & position name，再對 position name 進行比對，
		List<Position> positionList = positionMapper.getPositionName(partsId, importObjList.get(0).getStatusId(), deptId);
		Position target = null;
		for (Position position : positionList) {
			if (position.getPositionName().equals(importObjList.get(0).getPositionName())) {
				target = position;
				break;
			}
		}
		if (target == null || target.getId() < 1) {
			return false;
		}
		Integer positionId = target.getId();
		// 3. 判斷 partsInst 是否有相同的資料? 有: 加入數量, 沒有: 新建一筆資料
		
		return true;
	}
	
	
}
