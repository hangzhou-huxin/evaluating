package cn.itcast.application.study.evaluation.holland.web;

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
		
		ModelAndView mv = new ModelAndView( "holland/step1" ) ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		return  mv ;
	}
	
	@RequestMapping("/step2")
	public ModelAndView toStep2(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId){
		//System.out.println("-----h_r6_1=" + request.getParameter("h_r6_1")) ;
		String view = "holland/step2" ;
		String errMsg = "" ;
		
		if(StringUtils.isEmpty(evalId) || !EvaluationUtils.validateEvalId(evalId)){
			//中止测试,
			view = "holland/error" ;
			errMsg = "测试超时，请重新进行测试" ;
			
		}else{
			//取出第一步所提交题的结果存入redis
			Map params = WebUtil.getParamsToMap(request);
			System.out.println("params=" + params);
			EvaluationUtils.saveStepDataToCache(evalId, params);
			
		}
		
		ModelAndView mv = new ModelAndView( view ) ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		mv.addObject( "errMsg", errMsg) ;
		return mv ;
	}
	
	
	@RequestMapping("/step3")
	public ModelAndView toStep3(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId){
		String view = "holland/step3" ;
		String errMsg = "" ;
		ModelAndView mv = new ModelAndView( view ) ;
		if(StringUtils.isEmpty(evalId) || !EvaluationUtils.validateEvalId(evalId)){
			//中止测试,
			view = "holland/error" ;
			errMsg = "测试超时，请重新进行测试" ;
			mv.addObject( "errMsg", errMsg) ;
		}else{
			//取出第一步所提交题的结果存入redis
			Map params = WebUtil.getParamsToMap(request);
			EvaluationUtils.saveStepDataToCache(evalId, params);
			mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		}
		
		return mv ;
	}
	
	
	@RequestMapping("/step4")
	public ModelAndView toStep4(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId){
		String view = "holland/step4" ;
		String errMsg = "" ;
		ModelAndView mv = new ModelAndView( view ) ;
		if(StringUtils.isEmpty(evalId) || !EvaluationUtils.validateEvalId(evalId)){
			//中止测试,
			view = "/error" ;
			errMsg = "测试超时，请重新进行测试" ;
			mv.addObject( "errMsg", errMsg) ;
		}else{
			//取出第一步所提交题的结果存入redis
			Map params = WebUtil.getParamsToMap(request);
			EvaluationUtils.saveStepDataToCache(evalId, params);
			mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
			//EvaluationUtils.
		}
		
		return mv ;
	}
	
	
	@RequestMapping("/finish")
	public ModelAndView finish(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId){
		String view = "holland/report" ;
		ModelAndView mv = new ModelAndView( view ) ;
		Map params =  WebUtil.getParamsToMap(request);
		EvaluationUtils.saveStepDataToCache(evalId, params);
		
		mv.addObject( "params", EvaluationUtils.getCacheData(evalId) ) ;
		
		//开始计算得分
		Map<String,String> data = EvaluationUtils.getCacheData(evalId) ;
		Map<String,Integer> result = EvaluationUtils.getHollandEvaluationResult(data) ;
		mv.addObject( "result"	, result) ;
		System.out.println(result);
		return mv ;
	}

}
