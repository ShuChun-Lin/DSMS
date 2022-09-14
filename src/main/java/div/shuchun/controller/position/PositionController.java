package div.shuchun.controller.position;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import div.shuchun.pojo.Position;
import div.shuchun.pojo.User;
import div.shuchun.service.position.PositionService;
import div.shuchun.utils.Constants;

@Controller
public class PositionController {
	
	@Autowired
	private PositionService positionService;

	@ResponseBody
	@RequestMapping(value="/checkPosition", produces="application/json")
	public String getPositionByPartsId(HttpServletRequest request, String partsCode, String statusId) {
		
		// get partsCode, statusId, deptId from client
//		String partsCode = request.getParameter("partsCode");
//		String statusId = request.getParameter("statusId");
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		List<String> positionList = positionService.getPosition(partsCode, Integer.parseInt(statusId), user);
		System.out.println(positionList);
		return positionList.toString();
	}
}
