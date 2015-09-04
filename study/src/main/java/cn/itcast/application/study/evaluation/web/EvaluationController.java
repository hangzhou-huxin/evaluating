package cn.itcast.application.study.evaluation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/holland")
public class EvaluationController {
	
	@RequestMapping("/main")
	public ModelAndView init(){
		
		String evaluationId = java.util.UUID.randomUUID().toString() ;
		
		ModelAndView mv = new ModelAndView( "holland/first" ) ;
		mv.addObject("evId", evaluationId) ;
		return  mv ;
	}

}
