package cn.itcast.study.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.application.study.evaluation.core.EvaluationUtils;

public class EvaluationUtilsTest {
	
	@Test
	public void test(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-redis.xml","applicationContext-eval.xml"}) ;
		
		Map map = new HashMap() ;
		map.put("hu", "xin") ;
		EvaluationUtils.saveStepData("111", "step1", map);
	}

}
