package cn.itcast.application.study.evaluation.core;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	public final static String EVALUATION_ID_PARAM_NAME = "evId" ;
	
	public final static Long EXPIRE_MINUTES = 20l ;
	
	public final static String PREVIOUS_STEP_PARAM_NAME = "previous" ;
	
	public final static String NEXT_STEP_PARAM_NAME = "next" ;
	
	public final static String USER_NAME_PARAM_NAME = "username" ;
	
	public final static String QQ_PARAM_NAME	= "qq" ;
	
	public final static Map<Integer,String> educationMap = new HashMap<Integer,String>(){
		{
            put(1, "初中");
            put(2, "高中");
            put(3, "中专");
            put(4, "大专");
            put(5, "本科");
            put(6, "硕士");
            put(7, "博士");
        }
	}; 
	
	

}
