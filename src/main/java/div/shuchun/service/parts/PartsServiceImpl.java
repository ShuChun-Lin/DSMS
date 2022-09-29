package div.shuchun.service.parts;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import div.shuchun.dao.parts.PartsMapper;
import div.shuchun.dao.position.PositionMapper;
import div.shuchun.pojo.ImportObj;
import div.shuchun.pojo.Parts;
import div.shuchun.pojo.PartsInst;
import div.shuchun.pojo.Position;
import div.shuchun.pojo.SearchExportObj;
import div.shuchun.utils.JsonSupport;
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
		
		JsonSupport jtj = new JsonSupport();
		List<ImportObj> importObjList = jtj.jsonArrayToPartObjList(jsonArrayString);
		System.out.println("service: importObjList =>> " + importObjList);
		
		// 得到了前端傳入的 statusId partsCode quantity positionName，對每一筆資料進行處理
		for (int i=0; i < importObjList.size(); i++) {
			// 1. 取得 parts id (正常不會有錯，前端輸入時應該檢查過了)
			Integer partsId = partsMapper.getPartsIdByDept(importObjList.get(i).getPartsCode(), deptId);
			
			// 下面的判斷讓程式沒有 exception 拋出，所以事務沒有回滾
			/*if (partsId == null || partsId < 1) {  // 如果沒有找到對應的 partsId
			 *	System.out.println("service: partID fail");
			 *	return false;
			 *}
			 */
			
			// 2. 取得 position id
			//		先拿到所有對應的 position id & position name，再對 position name 進行比對，
			List<Position> positionList = positionMapper.getPositionName(partsId, importObjList.get(i).getStatusId(), deptId);
			Position target = null;
			for (Position position : positionList) {
				if (position.getPositionName().equals(importObjList.get(i).getPositionName())) {
					target = position;
					break;
				}
			}
			// 下面的判斷讓程式沒有 exception 拋出，所以事務沒有回滾
			/*if (target == null || target.getId() < 1) {
			 *	System.out.println("service: positionID fail");
			 *	return false;
			 *}
			 */
			
			Integer positionId = target.getId();
			// 3. 判斷 partsInst 是否有相同的資料? 有: 加入數量, 沒有: 新建一筆資料
			PartsInst partsInst = partsMapper.getPartsInst(partsId, importObjList.get(i).getStatusId(), positionId);
			
			if (partsInst == null) {
				// 新增一筆資料
				partsInst = new PartsInst(null, importObjList.get(i).getQuantity(),
						null, partsId, importObjList.get(i).getStatusId(),
						positionId);
				int result = partsMapper.addPartsInst(partsInst);
				if (result < 1) {
					System.out.println("service: add partsInst fail");
					return false;
				}
			} else {
				// 修改資料數量
				partsInst.setQuantity(partsInst.getQuantity() + importObjList.get(i).getQuantity());
				int result = partsMapper.updateQuantity(partsInst);
				if (result < 1) {  // 這個沒有意義，不會rollback
					System.out.println("service: update partsInst quantity fail");
					return false;
				}
			}
		}
		System.out.println("service: return true ok");
		return true;
	}

	@Override
	public List<String> getExportObjtList(String partsCode, Integer deptId, Integer statusId) {
		// get partsId
		Integer partsId = partsMapper.getPartsIdByDept(partsCode, deptId);
		if (partsId == null || partsId < 1) {
			return null;
		}
		
		// get export object list
		List<SearchExportObj> exportList = partsMapper.getExportObjtList(partsId, statusId);
		
		// export obj list turn to json string list
		List<String> partsInstList = new ArrayList<>();
		for (SearchExportObj exObj : exportList) {
			partsInstList.add(exObj.toStringAsJson());
		}
		
		return partsInstList;
	}
	
	@Override
	public boolean exportParts(String jsonArrayString, int deptId) {
		
		JsonSupport jtj = new JsonSupport();
		List<ImportObj> exportObjList = jtj.jsonArrayToPartObjList(jsonArrayString);
		System.out.println("service: exportObjList =>> " + exportObjList);
		
		// 得到了前端傳入的 statusId partsCode quantity positionName，對每一筆資料進行處理
		for (int i=0; i < exportObjList.size(); i++) {
			// 1. 取得 parts id (正常不會有錯，前端輸入時應該檢查過了)
			Integer partsId = partsMapper.getPartsIdByDept(exportObjList.get(i).getPartsCode(), deptId);
			
			// 2. 取得 position id
			//		先拿到所有對應的 position id & position name，再對 position name 進行比對，
			List<Position> positionList = positionMapper.getPositionName(partsId, exportObjList.get(i).getStatusId(), deptId);
			Position target = null;
			for (Position position : positionList) {
				if (position.getPositionName().equals(exportObjList.get(i).getPositionName())) {
					target = position;
					break;
				}
			}
			Integer positionId = target.getId();
			
			// 3. 判斷 partsInst 是否有相同的資料? 有: 減少數量, 沒有: 拋出異常
			PartsInst partsInst = partsMapper.getPartsInst(partsId, exportObjList.get(i).getStatusId(), positionId);
			
			if (partsInst == null) {
				// 拋出異常
				throw new NullPointerException();
			} else {
				// 先比較領出數量與現有數量
				if (exportObjList.get(i).getQuantity() > partsInst.getQuantity()) {  // 領出比庫存多
					// 拋出異常
					throw new NullPointerException();
				}
				if (exportObjList.get(i).getQuantity() == partsInst.getQuantity()) {  // 領出跟庫存一樣，刪除資料
					partsMapper.deletePartsInst(partsInst);
				} else {
					// 修改資料數量
					partsInst.setQuantity(partsInst.getQuantity() - exportObjList.get(i).getQuantity());
					int result = partsMapper.updateQuantity(partsInst);
				}
			}
		}
		System.out.println("service: return true ok");
		return true;
	}

	@Override
	public Integer getPartsIdByDept(String partsCode, int deptId) {
		Integer partsId = partsMapper.getPartsIdByDept(partsCode, deptId);
		return partsId;
	}

	@Override
	public boolean isPartsInstWithPositionIdExist(Integer positionId) {
		if (partsMapper.getRowCountOfPartsInstByPositionId(positionId) > 0) {
			return true;
		}
		return false;
	}
}
