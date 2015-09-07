package cn.itcast.application.study.evaluation.holland.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;

@Controller
@RequestMapping("/holland")
public class EvaluationController {
	
	@Autowired  
	private  HttpServletRequest request;
	
	@RequestMapping("/main")
	public ModelAndView init(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId ){
		//判断是否携带测试编号
		if(StringUtils.isEmpty(evalId)){
			//产生一个测试编号
			evalId = java.util.UUID.randomUUID().toString() ;
			
		}else{
			//校验测试编号
			if(!EvaluationUtils.validateEvalId(evalId)) {
				evalId = java.util.UUID.randomUUID().toString() ;
			}
		}
		
		
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
			//中止测试
			view = "holland/step1" ;
		}else{
			//取出第一步所提交题的结果存入redis
		}
		ModelAndView mv = new ModelAndView( view ) ;
		if(!StringUtils.isEmpty(errMsg))
			mv.addObject( "errMsg", errMsg) ;
		//mv.addObject("evId", evaluationId) ;
		return mv ;
	}
	
	
	@RequestMapping("/step3")
	public ModelAndView toStep3(){
		//System.out.println("-----h_r6_1=" + request.getParameter("h_r6_1")) ;
		ModelAndView mv = new ModelAndView( "holland/step3" ) ;
		//mv.addObject("evId", evaluationId) ;
		return mv ;
	}
	
	
	@RequestMapping("/step4")
	public ModelAndView toStep4(){
		//System.out.println("-----h_r6_1=" + request.getParameter("h_r6_1")) ;
		ModelAndView mv = new ModelAndView( "holland/step4" ) ;
		//mv.addObject("evId", evaluationId) ;
		return mv ;
	}

}
