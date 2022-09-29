package div.shuchun.controller.position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import div.shuchun.pojo.Position;
import div.shuchun.pojo.User;
import div.shuchun.service.parts.PartsService;
import div.shuchun.service.position.PositionService;
import div.shuchun.utils.Constants;

@Controller
public class PositionController {
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private PartsService partsService;

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
	
	@RequestMapping("/searchPosition")
	public String toSearchPositionPage() {
		return "searchPosition";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAreaPosition.do", produces="application/json")
	public String getAreaPosition(HttpServletRequest request, String areaId, String statusId, 
			String partsCode, String positionName) {
		
		System.out.println("statusId: " + statusId);
		List<String> areaPositionList = positionService.getSearchAreaPosition(Integer.parseInt(areaId),
				Integer.parseInt(statusId), partsCode, positionName);
		System.out.println(areaPositionList);
		return areaPositionList.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/addPosition.do", produces="application/json")
	public String addPosition(HttpServletRequest request, String positionName, String partsCode, 
			String positionStatus, String creationDate, String positionArea) {
		// get part id by deptId, part code
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		Integer partsId = null;
		if (partsCode != null && !partsCode.equals("")) {
			partsId = partsService.getPartsIdByDept(partsCode, deptId);
			if (partsId == null || partsId < 1) {
				return "{\"warning\":\"新增失敗(沒有此料號)\"}";
			}
		}
		
		Position position = new Position(null, positionName, 
				Integer.parseInt(positionArea), partsId, Integer.parseInt(positionStatus),
				user.getId(), new Date(Long.parseLong(creationDate)),
				null, null);
		
		if (!positionService.addPosition(position)) {
			return "{\"warning\":\"新增失敗(儲位名稱已存在 or 資料寫入異常)\"}";
		}
		
		return "{\"info\":\"新增成功\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/deletePosition.do", produces="application/json")
	public String deletePosition(HttpServletRequest request, String positionName, String positionArea) {
		
		if (!positionService.deletePosition(positionName, positionArea)) {
			return "{\"warning\":\"刪除失敗(找不到儲位 or 資料刪除異常 or 儲位使用中)\"}";
		}
		
		return "{\"info\":\"刪除成功\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/updatePosition.do", produces="application/json")
	public String updatePosition(HttpServletRequest request, String positionName, String partsCode, 
			String positionStatus, String modifyDate, String positionArea) {
		// get position id
		Integer positionId = positionService.getPositionId(Integer.parseInt(positionArea), positionName);
		if (positionId == null || positionId < 1) {
			return "{\"warning\":\"修改失敗，找不到該儲位ID\"}";
		}
		
		// check partsInst table is exist this position id (if it exists) the position can not be update
		if (partsService.isPartsInstWithPositionIdExist(positionId)) {
			return "{\"warning\":\"修改失敗，該儲位ID使用中\"}";
		}
		
		// get part id by deptId, part code
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		Integer partsId = null;
		if (partsCode != null && !partsCode.equals("")) {
			partsId = partsService.getPartsIdByDept(partsCode, deptId);
			if (partsId == null || partsId < 1) {
				return "{\"warning\":\"修改失敗(沒有此料號)\"}";
			}
		}
		
		Position position = new Position(positionId, positionName, 
				Integer.parseInt(positionArea), partsId, Integer.parseInt(positionStatus),
				null, null,
				user.getId(), new Date(Long.parseLong(modifyDate)));
		
		if (!positionService.updatePosition(position)) {
			return "{\"warning\":\"修改失敗(資料修改異常)\"}";
		}
		
		return "{\"info\":\"修改成功\"}";
	}
}
