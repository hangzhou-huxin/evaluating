package cn.itcast.application.study.evaluation.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.huxin.utils.redis.RedisOperationsDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import cn.itcast.application.study.evaluation.holland.dto.HollandForm;

public class EvaluationUtils {
	
	protected static RedisOperationsDao redisDao ;
	
	
	public static Boolean validateEvalId(String evalId){
		if(StringUtils.isEmpty(evalId)){
			return false ;
		}
		return redisDao.isExist(evalId);
	}
	
	/**
	 * 保存测试过程中产生的数据到缓存中
	 * @param evalId
	 * @param data
	 */
	public static void  saveStepDataToCache( final String evalId , final Map<String,String> data){
	    Map<String,String> cache = redisDao.getMap( evalId ) ;
	    if( cache == null){
	    	cache = new HashMap<String,String>() ;
	    }
	    
	    cache.putAll(data);
	    redisDao.addMap(evalId, cache);
	}
	
	public static Map getCacheData(final String evalId ){
		return redisDao.getMap( evalId ) ;
	}
	
	
	public static String CachemapToJson(Map<String,String> data){
		//{'':''}
		StringBuilder buffer = new StringBuilder() ;
		String prefix = "h_" ;
		
		
		return buffer.toString() ;
	}
	
	public static void putEvalId(String evalId){
		Map<String,String> data = new HashMap<String,String>() ;
		data.put( Constant.EVALUATION_ID_PARAM_NAME, evalId)  ;
	    redisDao.addMap(evalId, data);
	    redisDao.setExpireTime(evalId, Constant.EXPIRE_MINUTES);
	}
	
	/**
	 * 根据评分数据计算出评分结果
	 * @return
	 */
	public static Map<String,Integer> getHollandEvaluationResult(Map<String,String> data){
		String prefix = "h" ;
		String[] types = {"r","i","a","s","e","c"} ;
		Map<String,Integer> result = new HashMap<String,Integer>() ;
		
		
		//R(实际型),I(调查型),A(艺术型),S(社会型),E(事业型),C(常规型)
		int score = 0 ;
		
		
		for(int t=0 ; t<types.length ;t++){
			//前三部分每种类型有8道题（“是”得5分，“否”得0分）
			for(int i=1 ;i<4;i++){
				for(int j=1 ; j<9;j++){
					//h_r1_1
					String param = prefix + "_" + types[t] + i + "_" + j ;
					String value = data.get(param) ;
					if("true".equals(value)){
						score += 5 ;
					}
				}
			}
			//第四部分每种类型有2道题(A不符（1分） B较不符（2分） C一般（3分） D较符合（4分） E符合（5分）)
			for(int j=1 ; j<3;j++){
				//h_r1_1
				String param = prefix + "_" + types[t] + 4 + "_" + j ;
				String value = data.get(param) ;
				if("a".equals(value)){
					score += 1 ;
				}else if("b".equals(value)){
					score += 2 ;
				}else if("c".equals(value)){
					score += 3 ;
				}else if("d".equals(value)){
					score += 4 ;
				}else if("e".equals(value)){
					score += 5 ;
				}
			}
			result.put(types[t]	, score) ;
			score = 0; 
		}
		
		return result ;
	}
	
	
	public static String cacheDataToJSON(Map<String,String> cache ,Map<String,String> filter){
		if( filter == null){
			filter = new HashMap<String,String>() ;
		}
		StringBuilder buffer = new StringBuilder("{") ;
		for (Map.Entry<String, String> entry : cache.entrySet()) {
			String key = entry.getKey() ;
			String value = entry.getValue() ;
			if( filter.get(key) == null){
				buffer.append("'").append( key).append("':'").append(value).append("',") ;
			} 
		}
		
		int length = buffer.length() ;
		buffer.delete(length-1, length) ;
		buffer.append("}") ;
		return buffer.toString() ;
	}
	
	
	public static String cacheDataToJSON(Map<String,String> cache ,String prefix){
		
		StringBuilder buffer = new StringBuilder("{") ;
		for (Map.Entry<String, String> entry : cache.entrySet()) {
			String key = entry.getKey() ;
			String value = entry.getValue() ;
			if( key.startsWith(prefix) ){
				buffer.append("'").append( key).append("':'").append(value).append("',") ;
			} 
		}
		
		int length = buffer.length() ;
		buffer.delete(length-1, length) ;
		buffer.append("}") ;
		return buffer.toString() ;
	}
	
	
	/**
	 * 按照分数高低返回评分的类型字符串
	 * @param result
	 * @return
	 */
	public static String getOrderedResult(Map<String,Integer> result){
		

		List<Map.Entry<String, Integer>> newResult =
		    new ArrayList<Map.Entry<String, Integer>>(result.entrySet());

		

		//排序
		Collections.sort(newResult, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		    	if( o2.getValue() - o1.getValue() != 0){
		    		return o2.getValue().intValue() - o1.getValue().intValue() ;
		    	}else{
		    		return o1.getKey().compareTo(o2.getKey())  ;
		    	}
		    }
		}); 
		
		
		StringBuilder orderedResult = new StringBuilder() ;
		
		int size = newResult.size() ;
		for(int i=0;i<1;i++){
			orderedResult.append(newResult.get(i).getKey()) ;
		}
		
		//int len = orderedResult.length() ;
		//orderedResult.delete(len-1, len) ;
		return orderedResult.toString().toUpperCase() ;
	}
	
	
	public static Map<String,Integer> getEscapeEvaluationResult(Map<String,String> data){
		Map<String,Integer> result = new HashMap<String,Integer>() ;
		String prefix = Constant.ESCAPE_PREFIX ;
		Integer totalScore = 0 ;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			String key = entry.getKey() ;
			String value = entry.getValue() ;
			if( key.startsWith(prefix)){
				String[] keys = key.split("_") ;
				String dimension = keys[2] ;
				Integer dimensionScore = result.get(dimension) ;
				if(dimensionScore == null){
					result.put(dimension, Integer.parseInt(value)) ;
				}else{
					result.put(dimension, dimensionScore + Integer.parseInt(value)) ;
				}
				totalScore += Integer.parseInt(value) ;
			} 
		}
		result.put(Constant.ESCAPE_TOTAL_SCORE , totalScore) ;
		return result ;
	}

}
