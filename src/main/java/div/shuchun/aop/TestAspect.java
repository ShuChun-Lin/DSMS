package div.shuchun.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
public class TestAspect {
	
	@Before("execution(* div.shuchun.service.parts.*.*(..))")
	public void before() {
		System.out.println("====== target 執行前 by 註解 ======");
	}
	
	@After("execution(* div.shuchun.service.parts.*.*(..))")
	public void after() {
		System.out.println("====== target 執行後 by 註解 ======");
	}
}
