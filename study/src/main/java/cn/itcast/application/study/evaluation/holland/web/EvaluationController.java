package cn.itcast.application.study.evaluation.holland.web;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;
import cn.itcast.application.study.evaluation.utils.WebUtil;
import cn.itcast.application.study.manage.holland.dao.EvaluationResultDao;
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.service.HollandManageService;

@Controller
@RequestMapping("/holland")
public class EvaluationController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	@Autowired 
	private	HollandManageService	hollandManageService ;
	
	@RequestMapping("/main")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView( "holland/main" ) ;
		//mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		return  mv ;
	}
	
	@RequestMapping("/step1")
	public ModelAndView step1(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId,
							  @RequestParam(value=Constant.USER_NAME_PARAM_NAME,required=false) String username,
							  @RequestParam(value=Constant.QQ_PARAM_NAME,required=false) String qq){
		//缓存数据
		Map<String,String> data = new HashMap<String,String>() ;
		//第一次进入页面
		if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(qq)){
			data.put(Constant.USER_NAME_PARAM_NAME, username) ;
			data.put(Constant.QQ_PARAM_NAME, qq) ;
			EvaluationUtils.saveStepDataToCache(evalId, data);
		}
		
		//获取缓存中的测试数据,转测试数据为json
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache , null) ;
		
		//渲染页面
		ModelAndView mv = new ModelAndView( "holland/step1" ) ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		mv.addObject("cache",cacheJson) ;
		return  mv ;
	}
	
	@RequestMapping("/step2")
	public ModelAndView toStep2(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId){
		//System.out.println("-----h_r6_1=" + request.getParameter("h_r6_1")) ;
		String view = "holland/step2" ;
		String errMsg = "" ;
		ModelAndView mv = new ModelAndView(  ) ;
		
		//取出第一步所提交题的结果存入redis
		Map params = WebUtil.getParamsToMap(request);
		EvaluationUtils.saveStepDataToCache(evalId, params);
		
		//获取缓存中的测试数据,转测试数据为json
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache ,null) ;
		mv.addObject("cache",cacheJson) ;
		
		
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		mv.addObject( "errMsg", errMsg) ;
		
		mv.setViewName(view);
		return mv ;
	}
	
	
	@RequestMapping("/step3")
	public ModelAndView toStep3(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId,
			@RequestParam(value=Constant.PREVIOUS_STEP_PARAM_NAME,required=false) String previous){
		String view = "holland/step3" ;
		String errMsg = "" ;
		ModelAndView mv = new ModelAndView(  ) ;
			
		//取出所提交题的结果存入redis
		Map params = WebUtil.getParamsToMap(request);
		EvaluationUtils.saveStepDataToCache(evalId, params);
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		
		//获取缓存中的测试数据,转测试数据为json
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache ,null) ;
		mv.addObject("cache",cacheJson) ;
		
		if( !StringUtils.isEmpty(previous)){
			
			view = "holland/step1" ;   //返回上一步
		}
		mv.setViewName(view);
		return mv ;
	}
	
	
	@RequestMapping("/step4")
	public ModelAndView toStep4(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId ,
								@RequestParam(value=Constant.PREVIOUS_STEP_PARAM_NAME,required=false) String previous){
		String view = "holland/step4" ;
		String errMsg = "" ;
		ModelAndView mv = new ModelAndView(  ) ;
		//取出用户提交的数据并进行缓存
		Map params =  WebUtil.getParamsToMap(request);
		EvaluationUtils.saveStepDataToCache(evalId, params);
		//取出缓存的数据发送到页面
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache ,null) ;
		mv.addObject("cache",cacheJson) ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		
		if( !StringUtils.isEmpty(previous)){
			
			view = "holland/step2" ;   //返回上一步
		}
		mv.setViewName(view);
		return mv ;
	}
	
	
	@RequestMapping("/finish")
	public ModelAndView finish(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId,
								@RequestParam(value=Constant.PREVIOUS_STEP_PARAM_NAME,required=false) String previous,
								@RequestParam(value=Constant.NEXT_STEP_PARAM_NAME,required=false) String next){
		String ip = request.getRemoteAddr() ;
		String view = "holland/report" ;
		ModelAndView mv = new ModelAndView( ) ;
		
		Map params =  WebUtil.getParamsToMap(request);
		EvaluationUtils.saveStepDataToCache(evalId, params);
		
		//取出更新后的缓存数据
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		if( !StringUtils.isEmpty(previous)){
			//通过缓存数据展示页面
			String cacheJson = EvaluationUtils.cacheDataToJSON(cache ,null) ;
			mv.addObject("cache",cacheJson) ;
			view = "holland/step3" ;   //返回上一步
		}else{
			//通过缓存数据开始计算得分
			Map<String,Integer> result = EvaluationUtils.getHollandEvaluationResult(cache) ;
			//对评分结果进行排序
			String newResult = EvaluationUtils.getOrderedResult(result) ;
			mv.addObject("orderedResult", newResult) ;
			mv.addObject( "result"	, result) ;
			
			//保存评分数据
			Map<String,String> filter = new HashMap<String,String>() ;
			
			EvaluationResult evaluationResult = new EvaluationResult(); 
			evaluationResult.setrValue(result.get("r"));
			evaluationResult.setiValue(result.get("i"));
			evaluationResult.setsValue(result.get("s"));
			evaluationResult.setaValue(result.get("a"));
			evaluationResult.setcValue(result.get("c"));
			evaluationResult.seteValue(result.get("e"));
			
			evaluationResult.setIp(ip);
			
			evaluationResult.setEvaluationId(cache.get(Constant.EVALUATION_ID_PARAM_NAME));
			filter.put(Constant.EVALUATION_ID_PARAM_NAME, "") ;
			
			evaluationResult.setName(cache.get(Constant.USER_NAME_PARAM_NAME));
			filter.put(Constant.USER_NAME_PARAM_NAME, "") ;
			
			evaluationResult.setQq(cache.get(Constant.QQ_PARAM_NAME));
			filter.put(Constant.QQ_PARAM_NAME, "") ;
			filter.put(Constant.NEXT_STEP_PARAM_NAME, "") ;
			filter.put(Constant.PREVIOUS_STEP_PARAM_NAME, "") ;
			
			evaluationResult.setTalentsType(newResult);
			
			String cacheJson = EvaluationUtils.cacheDataToJSON(cache ,filter) ;
			evaluationResult.setContent(cacheJson);
			hollandManageService.saveResult(evaluationResult);
		}

		
		mv.setViewName(view);
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		return mv ;
	}
	
	
	

}
