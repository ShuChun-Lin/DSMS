package div.shuchun.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import div.shuchun.pojo.User;
import div.shuchun.service.user.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user")
	public String testUser(Model model) {
		List<User> userList = userService.getAllUserList();
		model.addAttribute("userList", userList);
		return "hello";
	}
}
