package div.shuchun.dao.parts;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import div.shuchun.pojo.Parts;
import div.shuchun.pojo.PartsInst;
import div.shuchun.pojo.SearchExportObj;

public interface PartsMapper {

	// get parts list by partsCode
	List<Parts> getPartsListByCode(@Param("partsCode") String partsCode, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
	
	// get parts instantiation count by partsId
	List<Integer> getPartsCountById(@Param("id") int id);
	
	// get parts data's total count for page
	int getPartsDataCount(@Param("partsCode") String partsCode);
	
	// get parts id by dept
	Integer getPartsIdByDept(@Param("partsCode") String partsCode, @Param("deptId") int deptId);
	
	// get partsInst id by partsId, statusId, positionId
	PartsInst getPartsInst(@Param("partsId") Integer partsId, @Param("statusId") Integer statusId, @Param("positionId") Integer positionId);
	
	// import a new partsInst data
	int addPartsInst(@Param("partsInst") PartsInst partsInst);
	
	// update quantity of a partsInst data
	int updateQuantity(PartsInst partsInst);
	
	// get export parts list (partsCode, partsName, quantity, area, position) by partsId, statusId
	List<SearchExportObj> getExportObjtList(@Param("partsId") Integer partsId, @Param("statusId") Integer statusId);
	
	// delete partsInst
	int deletePartsInst(PartsInst partsInst);
	
	// partsInst data counts with positionId
	int getRowCountOfPartsInstByPositionId(@Param("positionId") Integer positionId);
}
