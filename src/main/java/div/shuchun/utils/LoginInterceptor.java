package div.shuchun.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("start login interceptor . . .");
		if (request.getSession().getAttribute(Constants.USER_SESSION) != null) {
			System.out.println("have session");
			return true;
		}
		System.out.println("no session");
		response.sendRedirect("/DSMS/");
		return false;
	}
	
}
