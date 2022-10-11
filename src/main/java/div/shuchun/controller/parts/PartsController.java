package div.shuchun.controller.parts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import div.shuchun.pojo.Department;
import div.shuchun.pojo.ImportObj;
import div.shuchun.pojo.Parts;
import div.shuchun.pojo.Role;
import div.shuchun.pojo.User;
import div.shuchun.service.dept.DepartmentService;
import div.shuchun.service.parts.PartsService;
import div.shuchun.service.role.RoleService;
import div.shuchun.utils.Constants;
import div.shuchun.utils.PageSupport;

@Controller
public class PartsController {

	@Autowired
	private PartsService partsService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/searchParts")
	public String toSearchPartsPage() {
		return "searchParts";
	}
	
	@RequestMapping("/partSearch")
	public String getPartsListByCode(Model model, HttpServletRequest request) {
		
		// get queryPartsCode from front-end
		String queryPartsCode = request.getParameter("queryPartsCode");
		
		if (queryPartsCode == null || queryPartsCode.equals("")) {
			model.addAttribute("err", "未輸入料號");
			return "searchParts";
		}
		
		// get current page no. from front-end
		String pageIndex = request.getParameter("pageIndex");
		
		// get parts data count from database
		int totalCount = partsService.getPartsDataCount(queryPartsCode, null);
		
		// get entire page info
		PageSupport pageInfo = partsService.getPageSupportImpl(pageIndex, totalCount);
		
		// put page info in model
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", pageInfo.getCurrentPageNo());
		model.addAttribute("totalPageCount", pageInfo.getTotalPageCount());
		
		// search partsCode from database and put in model
		List<Parts> partsList = partsService.getPartsListByCode(queryPartsCode, pageInfo.getCurrentPageNo(), pageInfo.getPageSize());
		model.addAttribute("partsList", partsList);
		model.addAttribute("partsCode", queryPartsCode);  // 搜尋的值要留存
		
		return "searchParts";
	}
	
	@RequestMapping("/importParts")
	public String toImportPage() {
		return "import";
	}
	
	@RequestMapping(value="/importParts.do")
	@ResponseBody
	public String importParts(HttpServletRequest request, String tableInfo) {
		System.out.println("tableInfo: " + tableInfo);
		
		if (tableInfo == null || "".equals(tableInfo)) {
			System.out.println("controller: server do not get message");
			return "server do not get message";
		}
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		boolean result = partsService.importParts(tableInfo, deptId);

		if (result) {
			System.out.println("controller: success");
			return "success";
		}
		
		return "fail";
	}
	
	@RequestMapping("/exportParts")
	public String toExportPage() {
		return "export";
	}
	
	@ResponseBody
	@RequestMapping(value="/getPartsInstList.do", produces="application/json")
	public String getPartsInstListForExport(HttpServletRequest request, String partsCode, String statusId) {
		
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		
		List<String> partsInstList = partsService.getExportObjtList(partsCode, deptId, Integer.parseInt(statusId));
		System.out.println(partsInstList);
		
		// judge partsInstList size
		if (partsInstList == null || partsInstList.size() == 0) {
			return "{\"warning\":\"沒有此料\"}";
		}
		return partsInstList.toString();
	}
	
	@RequestMapping(value="/exportParts.do")
	@ResponseBody
	public String exportParts(HttpServletRequest request, String tableInfo) {
		System.out.println("tableInfo: " + tableInfo);
		
		if (tableInfo == null || "".equals(tableInfo)) {
			System.out.println("controller: server do not get message");
			return "server do not get message";
		}
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int deptId = user.getUserDepartment();
		boolean result = partsService.exportParts(tableInfo, deptId);
		
		if (result) {
			System.out.println("controller: success");
			return "success";
		}
		
		return "fail";
	}
	
	@RequestMapping("/toPartsListPage")
	public String toUserListPage(HttpServletRequest request, Model model) {
		
		List<Department> deptList = departmentService.getDeptList();
		model.addAttribute("deptList", deptList);
		
		// get parts data count from database
		int totalCount = partsService.getPartsDataCount(null, null);
		
		// to page => startIndex = 0 , pageIndex = 1
		PageSupport pageInfo = partsService.getPageSupportImpl("1", totalCount);
		
		// put page info in model
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", pageInfo.getCurrentPageNo());
		model.addAttribute("totalPageCount", pageInfo.getTotalPageCount());
		
		int startIndex = pageInfo.getPageSize() * (pageInfo.getCurrentPageNo() - 1);
		List<Parts> partsList = partsService.getAllPartsList(startIndex, pageInfo.getPageSize(), null, null);
		model.addAttribute("partsList", partsList);
		return "partsList";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchParts.do", produces="application/json")
	public String getPartsListByConditions(HttpServletRequest request, String partsCode, String deptId, String pageIndex) {
		
		List<String> result = new ArrayList<>();
		
		// get parts data count from database
		int totalCount = partsService.getPartsDataCount(partsCode, Integer.parseInt(deptId));
		result.add("{\"totalCount\":\"" + totalCount + "\"}");		
		
		// for page
		PageSupport pageInfo = partsService.getPageSupportImpl(pageIndex, totalCount);
		result.add("{\"currentPageNo\":\"" + pageInfo.getCurrentPageNo() + "\"}");
		result.add("{\"totalPageCount\":\"" + pageInfo.getTotalPageCount() + "\"}");
		
		// get parts list
		int startIndex = pageInfo.getPageSize() * (pageInfo.getCurrentPageNo() - 1);
		List<Parts> partsList = partsService.getAllPartsList(startIndex, 
				pageInfo.getPageSize(), partsCode, Integer.parseInt(deptId));
		for (Parts part : partsList) {
			result.add(part.toJsonString());
		}
		
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteParts.do", produces="application/json")
	public String deletePartsById(HttpServletRequest request, String pid) {
		
		int partsId = Integer.parseInt(pid);
		if (partsService.getPartsCountById(partsId) > 0) {  // 該 parts 有 partsinst 數量，不得刪除
			return "{\"delResult\":\"cannotdel\"}";
		}
		if (partsService.getPartsById(partsId) == null) {
			return "{\"delResult\":\"notexist\"}";
		}
		if (!partsService.deletePartsById(partsId)) {
			return "{\"delResult\":\"false\"}";
		}
		return "{\"delResult\":\"true\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/getUpdateParts.do", produces="application/json")
	public String getPartsForUpdate(HttpServletRequest request, String pid) {
		
		int partsId = Integer.parseInt(pid);
		Parts updateParts = partsService.getPartsById(partsId);
		if (updateParts == null) {
			return "{\"updateParts\":\"partsnotexist\"}";
		}
		
		return updateParts.toJsonString();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateParts.do", produces="application/json")
	public String updateParts(HttpServletRequest request, String partsFromView) {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		// transfer json to object
		JSONObject jsonObj = JSONObject.parseObject(partsFromView);

		Parts updateParts = new Parts(jsonObj.getInteger("id"),
								jsonObj.getString("partsCode"),
								jsonObj.getString("partsName"),
								jsonObj.getInteger("partsDepartment"),
								null, null,
								user.getId(),
								new Date(Long.parseLong(jsonObj.getString("currrentDate"))),
								null, null
								);
		// this parts id exist
		Parts makeSureParts = partsService.getPartsById(updateParts.getId());
		if (makeSureParts == null) {
			return "{\"result\":\"notexist\"}";
		}
		if (partsService.updateParts(updateParts)) {
			return "{\"result\":\"true\"}";
		}
		
		return "{\"result\":\"false\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/addParts.do", produces="application/json")
	public String addParts(HttpServletRequest request, String partsFromView) {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		// transfer json to object
		JSONObject jsonObj = JSONObject.parseObject(partsFromView);

		Parts addParts = new Parts(jsonObj.getInteger("id"),
				jsonObj.getString("partsCode"),
				jsonObj.getString("partsName"),
				jsonObj.getInteger("partsDepartment"),
				user.getId(),
				new Date(Long.parseLong(jsonObj.getString("currrentDate"))),
				null, null,
				null, null
				);
		
		// partsCode 料號 不可重複(can not reuse in the same department)
		if (partsService.getPartsIdByDept(addParts.getPartsCode(), addParts.getPartsDepartment()) != null) {
			System.out.println("add a parts result: partscodebeenused");
			return "{\"result\":\"partscodebeenused\"}";
		}
		
		if (partsService.addParts(addParts)) {
			return "{\"result\":\"true\"}";
		}
		
		return "{\"result\":\"false\"}";
	}
}
