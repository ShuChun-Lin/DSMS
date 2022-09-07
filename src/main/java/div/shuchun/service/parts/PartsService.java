package div.shuchun.service.parts;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Parts;

public interface PartsService {

	// get parts list by partsCode
	List<Parts> getPartsListByCode(String partsCode);
	
	// get parts instantiation count by partsId
	int getPartsCountById(int id);
	
	// get parts data's total count for page
	int getPartsDataCount(String partsCode);
}
