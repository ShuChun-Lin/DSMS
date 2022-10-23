package div.shuchun.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import div.shuchun.pojo.User;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		int userRole = user.getUserRole();
		
		if (userRole != 3) {
			System.out.println("權限OK");
			return true;
		}
		
		System.out.println("權限不足");
		response.sendRedirect("/DSMS/warning");
		return false;
	}
	
	
}
