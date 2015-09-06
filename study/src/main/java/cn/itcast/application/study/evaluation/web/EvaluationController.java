package cn.itcast.application.study.evaluation.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/holland")
public class EvaluationController {
	
	@Autowired  
	private  HttpServletRequest request;
	
	@RequestMapping("/main")
	public ModelAndView init(){
		System.out.println("测试111111111111");
		
		String evaluationId = java.util.UUID.randomUUID().toString() ;
		
		ModelAndView mv = new ModelAndView( "holland/first" ) ;
		mv.addObject("evId", evaluationId) ;
		return  mv ;
	}
	
	@RequestMapping("/step1")
	public ModelAndView dofirst(){
		System.out.println("-----h_r6_1=" + request.getParameter("h_r6_1")) ;
		ModelAndView mv = new ModelAndView( "holland/step2" ) ;
		//mv.addObject("evId", evaluationId) ;
		return mv ;
	}

}
