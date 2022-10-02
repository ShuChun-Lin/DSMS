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
	public List<String> getAreaList(Integer deptId, Integer statusId, String partsCode, String positionName) {
		// status input is 0, then be null -> sql do not judge 'statusId'
		System.out.println("statusId: " + statusId);
		System.out.println("partsCode: " + partsCode);
		System.out.println("positionName: " + positionName);

		List<Area> areaList = areaMapper.getAreaList(deptId, statusId, partsCode, positionName);
		
		List<String> areaJsonStringList = new ArrayList<>();
		for (Area area : areaList) {
			areaJsonStringList.add(area.toStringAsJson());
		}
		return areaJsonStringList;
	}

	@Override
	public boolean isAreaExist(Integer deptId, String areaName) {
		List<Area>arealist = areaMapper.getAreaByNameAndDeptId(deptId, areaName);
		if (arealist.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addArea(Area area, Integer deptId) {
		// check if any areaName in database is same as the area being going to add
		if(isAreaExist(deptId, area.getAreaName())) {
			return false;    // areaName already be used, do not add
		}
		
		int result = areaMapper.addArea(area);
		if (result < 1) {
			return false;
		}
		
		return true;
	}

	@Override
	public int deleteAreaById(Integer id) {
		return areaMapper.deleteAreaById(id);
	}

	@Override
	public boolean updateArea(Area area, Integer deptId) {
		// compare area name
		if (!area.getAreaName().equals(areaMapper.getAreaNameById(area.getId()))) {
			// if name has changed
			// check if any areaName in database is same as the new name which be updated
			if(isAreaExist(deptId, area.getAreaName())) {
				return false;    // areaName already be used, do not add
			}
		}
		
		int result = areaMapper.updateArea(area);
		if (result < 1) {
			return false;
		}
		
		return true;
	}

}
