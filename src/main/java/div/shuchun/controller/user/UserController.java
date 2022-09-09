package div.shuchun.controller.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import div.shuchun.pojo.User;
import div.shuchun.service.user.UserService;
import div.shuchun.utils.Constants;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.do")
	public String getLoginUser(Model model, HttpServletRequest request) {
		
		// get username and password from client
		String username = request.getParameter("username");
		String password = request.getParameter("pswd");
		
		// get loginUser by username and password
		User user = userService.getLoginUser(username, password);
		
		if (user != null) {
			// user info save in session
			request.getSession().setAttribute(Constants.USER_SESSION, user);
			return "home";
		} else {
			request.setAttribute("error", "帳號或密碼錯誤");
			return "forward:/";  // forward to login
		}
	}
	
	@RequestMapping("/logout.do")
	public String Logout(HttpServletRequest request, HttpServletResponse response) {
		
		// remove user info from session
		request.getSession().removeAttribute(Constants.USER_SESSION);
		
		return "redirect:/";  // redirect to login
	}
}
