package div.shuchun.dao.parts;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Parts;

public interface PartsMapper {

	// get parts list by partsCode
	List<Parts> getPartsListByCode(@Param("partsCode") String partsCode, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
	
	// get parts instantiation count by partsId
	List<Integer> getPartsCountById(@Param("id") int id);
	
	// get parts data's total count for page
	int getPartsDataCount(@Param("partsCode") String partsCode);
}
