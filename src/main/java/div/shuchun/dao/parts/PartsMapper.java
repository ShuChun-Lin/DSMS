package div.shuchun.dao.parts;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Parts;

public interface PartsMapper {

	// get parts list by partsCode
	List<Parts> getPartsListByCode(@Param("partsCode") String partsCode);
	
	// get parts instantiation count by partsId
	List<Integer> getPartsCountById(@Param("id") int id);
}
