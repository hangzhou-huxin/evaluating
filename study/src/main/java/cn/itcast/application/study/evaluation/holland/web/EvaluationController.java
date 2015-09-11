package cn.itcast.application.study.evaluation.holland.web;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

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

@Controller
@RequestMapping("/holland")
public class EvaluationController {
	
	@Autowired  
	private  HttpServletRequest request;
	
	@RequestMapping("/main")
	public ModelAndView init(){
		//进入测试页面，产生测试编号
		String evalId = java.util.UUID.randomUUID().toString() ;
		EvaluationUtils.putEvalId(evalId);
		ModelAndView mv = new ModelAndView( "holland/main" ) ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		return  mv ;
	}
	
	@RequestMapping("/step1")
	public ModelAndView step1(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId,
							  @RequestParam(value="username",required=false) String username,
							  @RequestParam(value="qq",required=false) String qq){
		//缓存数据
		Map<String,String> data = new HashMap<String,String>() ;
		//第一次进入页面
		if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(qq)){
			data.put("username", username) ;
			data.put("password", qq) ;
			EvaluationUtils.saveStepDataToCache(evalId, data);
		}
		
		//获取缓存中的测试数据,转测试数据为json
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache) ;
		
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
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache) ;
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
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache) ;
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
		String cacheJson = EvaluationUtils.cacheDataToJSON(cache) ;
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
		String view = "holland/report" ;
		ModelAndView mv = new ModelAndView( ) ;
		
		Map params =  WebUtil.getParamsToMap(request);
		EvaluationUtils.saveStepDataToCache(evalId, params);
		//System.out.println(params);
		//mv.addObject( "params", EvaluationUtils.getCacheData(evalId) ) ;
		
		if( !StringUtils.isEmpty(previous)){
			Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
			String cacheJson = EvaluationUtils.cacheDataToJSON(cache) ;
			mv.addObject("cache",cacheJson) ;
			view = "holland/step3" ;   //返回上一步
		}else{
			//开始计算得分
			Map<String,String> data = EvaluationUtils.getCacheData(evalId) ;
			Map<String,Integer> result = EvaluationUtils.getHollandEvaluationResult(data) ;
			mv.addObject( "result"	, result) ;
		}
		mv.setViewName(view);
		return mv ;
	}
	
	
	@RequestMapping("/apply")
	public ModelAndView apply(){
		String view = "holland/apply" ;
		ModelAndView mv = new ModelAndView( view ) ;
		return mv ;
	}

}
