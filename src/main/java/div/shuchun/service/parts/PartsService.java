package div.shuchun.service.parts;

import java.util.List;


import div.shuchun.pojo.Parts;
import div.shuchun.utils.PageSupport;

/**
 * Service of parts.
 * 
 * Deal with any service about parts.
 * 
 * @author shuchun.lin
 * @see PartsServiceImpl
 *
 */
public interface PartsService {

	/**
	 * Return a list of {@code Parts}
	 * 
	 * @param partsCode to search any {@code Parts} with matching value
	 * @param pageIndex to be used to calculate the start index of SQL's LIMIT
	 * @param pageSize to be the page size of SQL's LIMIT
	 * @return a list of {@code Parts}
	 */
	List<Parts> getPartsListWithPageLimitByCode(String partsCode, int pageIndex, int pageSize);
	
	// get parts instantiation count by partsId
	int getPartsCountById(int id);
	
	/**
	 * To get counts of data that matching conditions in the database
	 * 
	 * @param partsCode to be a searching condition
	 * @param deptId to be a searching condition
	 * @return a counts of data that matching conditions in the database
	 */
	int getPartsDataCount(String partsCode, Integer deptId);
	
	// get parts id by dept
	Integer getPartsIdByDept(String partsCode, int deptId);
	
	/**
	 * Get a instance of class {@code PageSupport}
	 * 
	 * @param pageIndex
	 * @param totalCount
	 * @return a instance of class {@code PageSupport}
	 * @see div.shuchun.utils.PageSupport
	 */
	PageSupport getPageSupportImpl(String pageIndex, int totalCount);
	
	// import partsInst from string type json array
	boolean importParts(String jsonArrayString, int deptId);
	
	// get export parts list as String (json)
	List<String> getExportObjtList(String partsCode, Integer deptId, Integer statusId);

	// export partsInst
	boolean exportParts(String jsonArrayString, int deptId);
	
	// check position id if have partsInst data
	boolean isPartsInstWithPositionIdExist(Integer positionId);
	
	// get all parts list
	List<Parts> getAllPartsList(Integer startIndex, Integer pageSize, String partsCode, Integer deptId);
	
	// get part by id
	Parts getPartsById(Integer id);
	
	// delete part by id
	boolean deletePartsById(Integer id);
	
	// update parts
	boolean updateParts(Parts parts);
	
	// insert parts
	boolean addParts(Parts parts);
}
