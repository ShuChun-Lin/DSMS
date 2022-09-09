package div.shuchun.service.parts;

import java.util.List;

import div.shuchun.dao.parts.PartsMapper;
import div.shuchun.pojo.Parts;
import div.shuchun.utils.PageSupport;

public class PartsServiceImpl implements PartsService {

	private PartsMapper partsMapper;

	public void setPartsMapper(PartsMapper partsMapper) {
		this.partsMapper = partsMapper;
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
	
	
}
