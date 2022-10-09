package div.shuchun.controller.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import div.shuchun.pojo.Department;
import div.shuchun.pojo.Role;
import div.shuchun.pojo.User;
import div.shuchun.service.dept.DepartmentService;
import div.shuchun.service.role.RoleService;
import div.shuchun.service.user.UserService;
import div.shuchun.utils.Constants;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DepartmentService departmentService;
	
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
	
	@RequestMapping("/toUserListPage")
	public String toUserListPage(Model model) {
		List<Role> roleList = roleService.getRoleList();
		model.addAttribute("roleList", roleList);
		
		List<Department> deptList = departmentService.getDeptList();
		model.addAttribute("deptList", deptList);
		System.out.println(deptList);
		
		List<User> userList = userService.getAllUserList();
		model.addAttribute("userList", userList);
		return "userList";
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteUser.do", produces="application/json")
	public String deleteUserById(HttpServletRequest request, String uid) {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int userId = Integer.parseInt(uid);
		if (user.getId() == userId) {
			return "{\"delResult\":\"cannotdel\"}";
		}
		if (userService.getUserById(userId) == null) {
			return "{\"delResult\":\"notexist\"}";
		}
		if (!userService.deleteUserById(userId)) {
			return "{\"delResult\":\"false\"}";
		}
		return "{\"delResult\":\"true\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchUser.do", produces="application/json")
	public String getUserByConditions(HttpServletRequest request, String userName, String roleId, String deptId) {
		List<User> userList = userService.getAllUserList(userName, Integer.parseInt(roleId), Integer.parseInt(deptId));
		
		// user transfer to string (json)
		List<String> stringList = new ArrayList<>();
		for (User user : userList) {
			stringList.add(user.toStringAsJson());
		}
		System.out.println(stringList);
		return stringList.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/getUpdateUser.do", produces="application/json")
	public String getUserForUpdate(HttpServletRequest request, String uid) {
		
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int userId = Integer.parseInt(uid);
		if (user.getId() == userId) {
			return "{\"updateUser\":\"cannotUpdate\"}";
		}
		User updateUser = userService.getUserById(userId);
		
		return updateUser.toStringAsJson();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateUser.do", produces="application/json")
	public String updateUser(HttpServletRequest request, String userFromView) {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		// transfer json to object
		JSONObject jsonObj = JSONObject.parseObject(userFromView);

		User updateUser = new User(jsonObj.getInteger("id"),
								jsonObj.getString("userCode"),
								jsonObj.getString("userName"),
								jsonObj.getString("userPassword"),
								jsonObj.getString("userId"),
								jsonObj.getInteger("userDepartment"),
								jsonObj.getInteger("userRole"),
								null,null,
								user.getId(),
								new Date(Long.parseLong(jsonObj.getString("currrentDate"))),
								null,null
								);
		// can not update user self
		if (user.getId() == updateUser.getId()) {
			return "{\"result\":\"cannotUpdate\"}";
		}
		// user id exist
		User makeSureUser = userService.getUserById(updateUser.getId());
		if (makeSureUser == null) {
			return "{\"result\":\"notexist\"}";
		}
		if (userService.updateUser(updateUser)) {
			return "{\"result\":\"true\"}";
		}
		
		return "{\"result\":\"false\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/addUser.do", produces="application/json")
	public String addUser(HttpServletRequest request, String userFromView) {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		
		// transfer json to object
		JSONObject jsonObj = JSONObject.parseObject(userFromView);

		User addUser = new User(jsonObj.getInteger("id"),
								jsonObj.getString("userCode"),
								jsonObj.getString("userName"),
								jsonObj.getString("userPassword"),
								jsonObj.getString("userId"),
								jsonObj.getInteger("userDepartment"),
								jsonObj.getInteger("userRole"),
								user.getId(),
								new Date(Long.parseLong(jsonObj.getString("currrentDate"))),
								null,null,
								null,null
								);
		
		// userId 工號 不可重複(can not reuse)
		if (userService.getUserByUserId(addUser.getUserId()) != null) {
			return "{\"result\":\"useridbeenused\"}";
		}
		
		if (userService.addUser(addUser)) {
			return "{\"result\":\"true\"}";
		}
		
		return "{\"result\":\"false\"}";
	}
}
