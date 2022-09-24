package div.shuchun.controller.area;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import div.shuchun.pojo.User;
import div.shuchun.service.area.AreaService;
import div.shuchun.utils.Constants;

@Controller
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	@ResponseBody
	@RequestMapping(value="/getArea.do", produces="application/json")
	public String getAreaList(HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		
		List<String> areatList = areaService.getAreaList(deptId);
		System.out.println(areatList);
		
		// judge partsInstList size
		if (areatList == null || areatList.size() == 0) {
			return "{\"warning\":\"沒有找到儲區\"}";
		}
		return areatList.toString();
	}
}
