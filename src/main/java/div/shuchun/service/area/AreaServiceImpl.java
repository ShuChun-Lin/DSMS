package div.shuchun.service.area;

import java.util.ArrayList;
import java.util.List;

import div.shuchun.dao.area.AreaMapper;
import div.shuchun.pojo.Area;

public class AreaServiceImpl implements AreaService {
	
	private AreaMapper areaMapper;
	
	public void setAreaMapper(AreaMapper areaMapper) {
		this.areaMapper = areaMapper;
	}

	@Override
	public List<String> getAreaList(Integer deptId) {
		List<Area> areaList = areaMapper.getAreaList(deptId);
		
		List<String> areaJsonStringList = new ArrayList<>();
		for (Area area : areaList) {
			areaJsonStringList.add(area.toStringAsJson());
		}
		return areaJsonStringList;
	}

}
