package div.shuchun.controller.area;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import div.shuchun.pojo.Area;
import div.shuchun.pojo.User;
import div.shuchun.service.area.AreaService;
import div.shuchun.utils.Constants;

@Controller
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	@ResponseBody
	@RequestMapping(value="/getArea.do", produces="application/json")
	public String getAreaList(HttpServletRequest request, String statusId, String partsCode, String positionName) {
		
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		
		List<String> areatList = areaService.getAreaList(deptId, Integer.parseInt(statusId), partsCode, positionName);
		System.out.println(areatList);
		System.out.println(areatList.size());
		
		// judge areaList size
		if (areatList == null || areatList.size() == 0) {
			return "{\"warning\":\"沒有找到儲區\"}";
		}
		return areatList.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/addArea.do", produces="application/json")
	public String addArea(HttpServletRequest request, String areaName, String areaDesc, String creationDate) {
		
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		int userId = user.getId();
		
		System.out.println("areaName: " + areaName);
		System.out.println("areaDesc: " + areaDesc);
		System.out.println("creationDate: " + creationDate);
		System.out.println("JavaDate: " + System.currentTimeMillis());
		
		Area area = new Area();
		area.setAreaName(areaName);
		area.setAreaDesc(areaDesc);
		
		Date date = new Date(Long.parseLong(creationDate));
		
		area.setCreationDate(date);
		area.setAreaDepartment(deptId);
		area.setCreatedBy(userId);
		
		if (areaService.addArea(area, deptId)) {
			return "{\"info\":\"新增成功\"}";
		}
	
		return "{\"warning\":\"新增失敗(儲區已存在)\"}";
	}
}
