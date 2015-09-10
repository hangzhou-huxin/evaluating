package cn.itcast.study.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.itcast.application.study.evaluation.utils.WebUtil;
public class WebUtilTest {

	@Test
	public void test(){
		Map params = new HashMap() ;
		params.put("p1", new String[]{"v1"}) ;
		params.put("p2", new String[]{"v2"}) ;
		params.put("p3", new String[]{"v3"}) ;
		//String json = WebUtil.getParamsToJSON(params) ;
		//System.out.println(json);
	}
}
