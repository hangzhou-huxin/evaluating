package cn.itcast.application.study.manage.holland.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/manage")
public class ManageController {

	@RequestMapping("/main.do")
	public ModelAndView main(){
		return new ModelAndView("manage/main") ;
	}
}
