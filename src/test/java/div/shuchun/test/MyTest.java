package div.shuchun.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import div.shuchun.pojo.User;
import div.shuchun.service.parts.PartsService;
import div.shuchun.service.position.PositionService;
import div.shuchun.service.user.UserService;

public class MyTest {

//	@Test
	public void testGetAllUser() {		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userServiceImpl");
		for (User user : userService.getAllUserList()) {
			System.out.println(user);
		}
		
		((ConfigurableApplicationContext)context).close();  // 用來關閉 context
	}
	
//	@Test
	public void testPartCount() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PartsService partsService = (PartsService) context.getBean("partsServiceImpl");
		
		System.out.println(partsService.getPartsCountById(3));
		
		((ConfigurableApplicationContext)context).close();  // 用來關閉 context
	}
	
//	@Test
	public void testGetPartList() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PartsService partsService = (PartsService) context.getBean("partsServiceImpl");
		
		System.out.println(partsService.getPartsListByCode("S000GEAR002", 2, 1));
		
		((ConfigurableApplicationContext)context).close();  // 用來關閉 context
	}
	
//	@Test
	public void testGetPosition() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PositionService positionService = (PositionService) context.getBean("positionServiceImpl");
		
		System.out.println(positionService.getPosition("S000GEAR002", 1, new User()));
		
		((ConfigurableApplicationContext)context).close();  // 用來關閉 context
	}
	
//	@Test
	public void testgetExportObjtList() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PartsService partsService = (PartsService) context.getBean("partsServiceImpl");
		
		System.out.println(partsService.getExportObjtList("S000GEAR001", 1, 2));
		
		((ConfigurableApplicationContext)context).close();  // 用來關閉 context
	}
	
	@Test
	public void testgetSearchAreaPosition() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PositionService positionService = (PositionService) context.getBean("positionServiceImpl");
		
		System.out.println(positionService.getSearchAreaPosition(1, null, null, null));
		
		((ConfigurableApplicationContext)context).close();  // 用來關閉 context
	}
}



