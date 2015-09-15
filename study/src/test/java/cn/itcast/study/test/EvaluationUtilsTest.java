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
		
		//Map map = new HashMap() ;
		//map.put("hu1", "xin1") ;
		String evalId = "db76a04a-ed85-4a12-a26a-1521ec80f478" ;
		//EvaluationUtils.putEvalId(evalId);
		//EvaluationUtils.saveStepDataToCache(evalId, map);
		
		System.out.println(EvaluationUtils.getCacheData(evalId)) ;
	}
	
	@Test
	public void test1(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-redis.xml","applicationContext-eval.xml"}) ;
		String id = java.util.UUID.randomUUID().toString() ;
		EvaluationUtils.putEvalId(id);
	}
	
	@Test
	public void test2(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-redis.xml","applicationContext-eval.xml"}) ;
		//String id = "860c40b7-fee6-4f6d-959c-0d58d832e76c";
		
		String id = "dddd" ;
		EvaluationUtils.putEvalId(id);
		Boolean flag = EvaluationUtils.validateEvalId(id) ;
		System.out.println(flag);
	}
	
	@Test
	public void testGetOrderedResult(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-redis.xml","applicationContext-eval.xml"}) ;
		Map<String,Integer> map = new HashMap<String,Integer>() ;
		map.put("R", 34) ;
		map.put("E", 34) ;
		map.put("S", 342) ;
		map.put("C", 78) ;
		map.put("I", 34) ;
		map.put("A", 78) ;
		String result = EvaluationUtils.getOrderedResult(map) ;
		
		System.out.println(result);
	}

}
