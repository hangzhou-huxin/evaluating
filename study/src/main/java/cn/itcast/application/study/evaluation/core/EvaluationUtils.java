package cn.itcast.application.study.evaluation.core;

import java.util.HashMap;
import java.util.Map;

import org.huxin.utils.redis.RedisOperationsDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import cn.itcast.application.study.evaluation.holland.dto.HollandForm;

public class EvaluationUtils {
	
	protected static RedisOperationsDao stringOperationDao ;
	
	
	public static Boolean validateEvalId(String evalId){
		return stringOperationDao.find(evalId) == null  ;
	}
	
	public static void  saveStepData( final String evalId , final String step ,final Map data){
	    final StringRedisTemplate redisTemplate =  stringOperationDao.getRedisTemplate() ;
	    redisTemplate.execute(new RedisCallback<Object>(){

			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				BoundHashOperations<String,String,Map> boundHashOperations = redisTemplate.boundHashOps( evalId) ;
				Map<String, Map> stepData = new HashMap<String, Map>();  
				stepData.put(step, data); 
	            boundHashOperations.putAll(stepData); 
				return null;
			}
	    	
	    }) ;
	}
	
	public static Map getStepData(final String evalId , final String step ){
		final StringRedisTemplate redisTemplate =  stringOperationDao.getRedisTemplate() ;
	    return  (Map)redisTemplate.execute(new RedisCallback<Object>(){

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				BoundHashOperations<String,String,Map> boundHashOperations = redisTemplate.boundHashOps( evalId) ;
				Map<String, Map> data = boundHashOperations.entries();  
				if( data != null){
					return data.get(step) ;
				}else{
					return null;
				}
			}
	    	
	    });
	}

}
