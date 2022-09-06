package div.shuchun.controller.parts;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import div.shuchun.pojo.User;
import div.shuchun.service.parts.PartsService;
import div.shuchun.utils.Constants;

@Controller
public class PartsController {

	@Autowired
	private PartsService partsService;
	
	@RequestMapping("/partSearch")
	public String getPartsListByCode(Model model, HttpServletRequest request) {
		
		// get username and password from client
		String username = request.getParameter("username");
		String password = request.getParameter("pswd");
	
		
		if (user != null) {
			// user info save in session
			request.getSession().setAttribute(Constants.USER_SESSION, user);
			model.addAttribute("user", user.getUserName());
			return "home";
		} else {
			request.setAttribute("error", "帳號或密碼錯誤");
			return "forward:/";  // forward to login
		}
	}
}
