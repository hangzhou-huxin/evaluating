package cn.itcast.application.study.evaluation.holland.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserApplyController {
	
	
	@RequestMapping("/apply")
	public ModelAndView execute(){
		String view = "apply" ;
		ModelAndView mv = new ModelAndView( view ) ;
		return mv ;
	}

}
