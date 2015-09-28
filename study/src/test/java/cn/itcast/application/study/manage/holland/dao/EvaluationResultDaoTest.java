package cn.itcast.application.study.manage.holland.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.application.study.manage.holland.domain.EvaluationResult;

public class EvaluationResultDaoTest {
	
	@Test
	public void testAdd(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-manage.xml"}) ;
		EvaluationResult result = new EvaluationResult() ;
		result.setName("胡欣");
		result.setQq("33324324");
		result.setEvaluationId("ddddd");
		//result.setType("holland");
		result.setContent("dddddddddddddddd");
		EvaluationResultDao dao = context.getBean(EvaluationResultDao.class) ;
		dao.add(result);
	}

}
