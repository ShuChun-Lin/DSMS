package div.shuchun.service.parts;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Parts;
import div.shuchun.utils.PageSupport;

public interface PartsService {

	// get parts list by partsCode
	List<Parts> getPartsListByCode(String partsCode, int pageIndex, int pageSize);
	
	// get parts instantiation count by partsId
	int getPartsCountById(int id);
	
	// get parts data's total count for page
	int getPartsDataCount(String partsCode);
	
	// get page info
	PageSupport getPageSupportImpl(String pageIndex, int totalCount);
	
	// get result(boolean) from string type json array
	boolean importParts(String jsonArrayString, int deptId);
}
