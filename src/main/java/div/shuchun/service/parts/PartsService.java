package div.shuchun.service.parts;

import java.util.List;


import div.shuchun.pojo.Parts;
import div.shuchun.utils.PageSupport;

public interface PartsService {

	// get parts list by partsCode
	List<Parts> getPartsListByCode(String partsCode, int pageIndex, int pageSize);
	
	// get parts instantiation count by partsId
	int getPartsCountById(int id);
	
	// get parts data's total count for page
	int getPartsDataCount(String partsCode);
	
	// get parts id by dept
	Integer getPartsIdByDept(String partsCode, int deptId);
	
	// get page info
	PageSupport getPageSupportImpl(String pageIndex, int totalCount);
	
	// import partsInst from string type json array
	boolean importParts(String jsonArrayString, int deptId);
	
	// get export parts list as String (json)
	List<String> getExportObjtList(String partsCode, Integer deptId, Integer statusId);

	// export partsInst
	boolean exportParts(String jsonArrayString, int deptId);
	
	// check position id if have partsInst data
	boolean isPartsInstWithPositionIdExist(Integer positionId);
}
