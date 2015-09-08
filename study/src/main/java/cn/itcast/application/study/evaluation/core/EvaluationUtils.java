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
	
	protected static RedisOperationsDao redisDao ;
	
	
	public static Boolean validateEvalId(String evalId){
		return redisDao.isExist(evalId);
	}
	
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
	
	public static void putEvalId(String evalId){
	    redisDao.addMap(evalId, new HashMap<String,String>());
	    redisDao.setExpireTime(evalId, Constant.EXPIRE_MINUTES);
	}

}
