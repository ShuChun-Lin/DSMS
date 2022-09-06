package div.shuchun.service.parts;

import java.util.List;

import div.shuchun.pojo.Parts;

public interface PartsService {

	// get parts list by partsCode
	List<Parts> getPartsListByCode(String partsCode);
	
	// get parts instantiation count by partsId
	int getPartsCountById(int id);
}
