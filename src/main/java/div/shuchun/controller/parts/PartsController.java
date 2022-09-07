package div.shuchun.controller.parts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import div.shuchun.pojo.Parts;
import div.shuchun.pojo.User;
import div.shuchun.service.parts.PartsService;
import div.shuchun.utils.Constants;
import div.shuchun.utils.PageSupport;

@Controller
public class PartsController {

	@Autowired
	private PartsService partsService;
	
	@RequestMapping("/partSearch")
	public String getPartsListByCode(Model model, HttpServletRequest request) {
		
		// get queryPartsCode from front-end
		String queryPartsCode = request.getParameter("queryPartsCode");
		
		if (queryPartsCode == null || queryPartsCode.equals("")) {
			model.addAttribute("err", "未輸入料號");
			return "home";
		}
		
		// search partsCode from database and put in model
		List<Parts> partsList = partsService.getPartsListByCode(queryPartsCode);
		model.addAttribute("partsList", partsList);
		
		// get current page no. from front-end
		String pageIndex = request.getParameter("pageIndex");
		// get parts data count from database
		int totalCount = partsService.getPartsDataCount(queryPartsCode);
		
		// calculate page
		PageSupport pageSupport = new PageSupport();
		pageSupport.pageCalculation(pageIndex, totalCount);
		
		// put page info in model
//		request.setAttribute("totalCount", totalCount);
//		request.setAttribute("currentPageNo", pageSupport.getCurrentPageNo());
//		request.setAttribute("totalPageCount", pageSupport.getTotalPageCount());
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", pageSupport.getCurrentPageNo());
		model.addAttribute("totalPageCount", pageSupport.getTotalPageCount());
		
		model.addAttribute("partsCode", queryPartsCode);  // 搜尋的值要留存
		
		return "home";
	}
}
