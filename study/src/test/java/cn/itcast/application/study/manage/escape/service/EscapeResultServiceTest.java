package cn.itcast.application.study.manage.escape.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.application.study.manage.escape.domain.EscapeResult;

public class EscapeResultServiceTest {

	@Test
	public void testSave(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-escape-manage.xml"}) ;
		EscapeResultService escapeResultService = context.getBean(EscapeResultService.class) ;
		
		EscapeResult escapeResult = new EscapeResult() ;
		   escapeResult.setCategoryId(1);
		   escapeResult.setEvalId("eeeeeeeeeeeeeeeee");
		   escapeResult.setQq("234324");
		   escapeResult.setUserName("hu");
		escapeResultService.save(escapeResult);
	}
}
