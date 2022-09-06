package div.shuchun.service.parts;

import java.util.List;

import div.shuchun.dao.parts.PartsMapper;
import div.shuchun.pojo.Parts;

public class PartsServiceImpl implements PartsService {

	private PartsMapper partsMapper;

	public void setPartsMapper(PartsMapper partsMapper) {
		this.partsMapper = partsMapper;
	}

	@Override
	public List<Parts> getPartsListByCode(String partsCode) {
		List<Parts> partsList = partsMapper.getPartsListByCode(partsCode);
		
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
	
	
}
