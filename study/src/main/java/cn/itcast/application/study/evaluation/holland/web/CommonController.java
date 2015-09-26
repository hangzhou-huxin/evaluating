package cn.itcast.application.study.evaluation.holland.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;

@Controller
public class CommonController {
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;

	@RequestMapping("/getCache")
	public ModelAndView execute(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId){
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		mv.addObject("cache", cache); 
	    return mv ;
	}
}
