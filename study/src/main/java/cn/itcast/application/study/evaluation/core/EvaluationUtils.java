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
	    redisDao.addMap(evalId, new HashMap<String,String>());
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

}
