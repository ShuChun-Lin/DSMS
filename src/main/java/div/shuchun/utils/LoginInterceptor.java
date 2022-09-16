package div.shuchun.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("start login interceptor . . .");
		// 判斷 session 是否過期
		if (request.getSession().getAttribute(Constants.USER_SESSION) != null) {
			System.out.println("have session");
			return true;
		}
		
		System.out.println("no session");
		// 判斷如果是 ajax 請求
		if (request.getHeader("x-requested-with") != null 
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			response.setHeader("sessionstatus", "timeout");  // 響應頭設定 session 狀態
			return false;
		}
		response.sendRedirect("/DSMS/");
		return false;
	}
	
}
