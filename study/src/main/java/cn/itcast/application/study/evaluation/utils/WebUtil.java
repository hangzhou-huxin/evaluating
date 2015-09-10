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
	
	
	public static String getParamsToJSON(HttpServletRequest request ){
		
		Map<String,String[]> params = request.getParameterMap() ;
		StringBuilder buffer = new StringBuilder("{") ;
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			   buffer.append("'").append( entry.getKey()).append("':'").append(entry.getValue()[0]).append("',") ;
		}
		
		int length = buffer.length() ;
		buffer.delete(length-1, length) ;
		buffer.append("}") ;
		return buffer.toString() ;
	}

}
