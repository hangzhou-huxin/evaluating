package cn.itcast.application.study.evaluation.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	public static Map<String,String> getParamsToMap(HttpServletRequest request ){
		Map<String,String[]> params = request.getParameterMap() ;
		Map<String,String> newParams = new HashMap<String,String>() ;
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			   newParams.put( entry.getKey() , entry.getValue()[0]);
		}
		return newParams; 
	}
	
	
	

}
