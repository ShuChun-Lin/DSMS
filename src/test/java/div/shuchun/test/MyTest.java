package div.shuchun.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import div.shuchun.pojo.User;
import div.shuchun.service.user.UserService;

public class MyTest {

	@Test
	public void testGetAllUser() {		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userServiceImpl");
		for (User user : userService.getAllUserList()) {
			System.out.println(user);
		}
		
		((ConfigurableApplicationContext)context).close();  // 用來關閉 context
	}
}
