package div.shuchun.controller.parts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

/**
 * Controller of parts.
 * 
 * Deal with any request about parts.
 * 
 * @author shuchun.lin
 * @see div.shuchun.service.parts.PartsService
 * @see div.shuchun.service.dept.DepartmentService
 */
@Controller
public class PartsController {

	@Autowired
	private PartsService partsService;
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * Forward to the page which is used to search specified parts
	 * 
	 * @return name of the searchParts.jsp
	 */
	@RequestMapping(value = "/searchPartsPage", method = RequestMethod.GET)
	public String toSearchPartsPage() {
		return "searchParts";
	}
	
	/**
	 * not RESTful API, use partsCode which from {@code HttpServletRequest} to
	 * search data {@link Parts} (includes each {@link Parts}'s quantity) from all departments in database.
	 * Forward to same page with new attributes of model.
	 *  
	 * @param model : to control elements of jsp, means control some attributes of view
	 * @param request : to get params from client like : partsCode, 
	 * @return name of the searchParts.jsp with model that have some attributes
	 */
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
		
		// get parts data counts from database
		int totalCount = partsService.getPartsDataCount(queryPartsCode, null);
		
		// get entire page info
		PageSupport pageInfo = partsService.getPageSupportImpl(pageIndex, totalCount);
		
		// put page info in model
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", pageInfo.getCurrentPageNo());
		model.addAttribute("totalPageCount", pageInfo.getTotalPageCount());
		
		// search partsCode from database and put in model
		List<Parts> partsList = partsService.getPartsListWithPageLimitByCode(queryPartsCode, pageInfo.getCurrentPageNo(), pageInfo.getPageSize());
		model.addAttribute("partsList", partsList);
		model.addAttribute("partsCode", queryPartsCode);  // 搜尋的值要留存在 input text 中
		
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
		
		// get user info from session
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		// if user's role is not ADMIN, set deptId
		Integer deptId = null;
		if (user.getUserRole() != 1) {
			deptId = user.getUserDepartment();
		}
		
		int startIndex = pageInfo.getPageSize() * (pageInfo.getCurrentPageNo() - 1);
		List<Parts> partsList = partsService.getAllPartsList(startIndex, pageInfo.getPageSize(), null, deptId);
		model.addAttribute("partsList", partsList);
		return "partsList";
	}
	
//*************************************************************************************************	

	/**
	 * RESTFul API
	 * To get list of {@link Parts} with one page from database
	 * and page info.
	 * 
	 * @param partsCode to be a condition for search
	 * @param deptId to be a condition for search
	 * @param pageIndex to be used to calculate the start index of SQL's LIMIT
	 * @return a JSON String includes a list of {@code Parts}, 
	 *         page info: currentPageNo、totalPageCount、totalCount
	 *         and a list of {@code Parts}
	 */
	@ResponseBody
	@GetMapping(value="/parts/{pageIndex}/{deptId}/{partsCode}", produces="application/json")
	public String getPartsListByConditions(@PathVariable("partsCode") String partsCode, 
											@PathVariable("deptId") String deptId, 
											@PathVariable("pageIndex") String pageIndex) {
		
		List<String> result = new ArrayList<>();
		
		// get total parts data counts from database
		int totalCount = partsService.getPartsDataCount(partsCode, Integer.parseInt(deptId));
		result.add("{\"totalCount\":\"" + totalCount + "\"}");		
		
		// get page info
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
	
	/**
	 * To get list of {@link Parts} with one page from database
	 * and page info.
	 * (without a condition {@code partsCode})
	 * 
	 * @param deptId to be a condition for search
	 * @param pageIndex to be used to calculate the start index of SQL's LIMIT
	 * @return a JSON String includes a list of {@code Parts}, 
	 *         page info: currentPageNo、totalPageCount、totalCount
	 *         and a list of {@code Parts}
	 */
	@ResponseBody
	@GetMapping(value="/parts/{pageIndex}/{deptId}", produces="application/json")
	public String getPartsListByConditions(@PathVariable("deptId") String deptId, 
											@PathVariable("pageIndex") String pageIndex) {
		
		return getPartsListByConditions(null, deptId, pageIndex);
	}
//*************************************************************************************************	
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
	
	/* 方法一 : 用POST + _method:PUT + web.xml用<filter>HiddenHttpMethodFilter
	 *  前端: 
	 *  $.ajax({
				type:"POST",
				url:"/DSMS/" + thisURL,
				data:{
					_method : "PUT" ,
					partsFromView : JSON.stringify(inputParts)
				},
				
	*  後端:
	@ResponseBody
	@PutMapping(value="/parts/{id}", produces="application/json")
	public String updateParts(HttpServletRequest request, String partsFromView) {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		System.out.println(partsFromView);
		
		// transfer json to object
		JSONObject jsonObj = JSONObject.parseObject(partsFromView);
		
		if (jsonObj == null) System.out.println("jsonObj is null");
		else System.out.println("jsonObject is not null");
		
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
	* 以上為方法一
	*/
	
	/* 方法二 : 用PUT + 前端 contentType:"application/json" 和 data 要用 json 字串
	 *  前端 :
	 *  $.ajax({
				type:"PUT",
				url:"/DSMS/" + thisURL,
				contentType:"application/json",
				data:JSON.stringify(inputParts),
	 * 
	 * 後端參數用 @RequestBody Parts 去接 (交給 jackson 去做)
	 * 所以要配置 jackson.core、.databind、.annotations 包
	 */
	@ResponseBody
	@PutMapping(value="/parts/{id}", produces="application/json")
	public String updateParts(HttpServletRequest request, @RequestBody Parts partsFromView) {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		System.out.println(partsFromView);
		
//		Parts updateParts = new Parts(partsFromView.getId(),
//								partsFromView.getPartsCode(),
//								partsFromView.getPartsName(),
//								partsFromView.getPartsDepartment(),
//								null, null,
//								user.getId(),
//								partsFromView.getModifyDate(),
//								null, null
//								);
		partsFromView.setModifyBy(user.getId());  // 用這個比新建updateParts更快
		
		// this parts id exist
		Parts makeSureParts = partsService.getPartsById(partsFromView.getId());
		if (makeSureParts == null) {
			return "{\"result\":\"notexist\"}";
		}
		if (partsService.updateParts(partsFromView)) {
			return "{\"result\":\"true\"}";
		}
		
		return "{\"result\":\"false\"}";
	}
	
	@ResponseBody
	@PostMapping(value="/parts", produces="application/json")
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
