package cn.itcast.application.study.manage.holland.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.service.HollandManageService;
import cn.itcast.application.study.utils.Page;
import cn.itcast.application.study.utils.PageResult;


@Controller
@RequestMapping("/manage/holland")
public class HollandManageController {
	
	@Autowired
	private HollandManageService hollandManageService  ;
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	
	@RequestMapping("/configTemplate.do")
	public ModelAndView configTemplate(){
		return new ModelAndView("manage/holland/template") ;
	}

	
	@RequestMapping("/list.do")
	public ModelAndView gotoList(){
		
		return new ModelAndView("manage/holland/list") ;
	}
	
	
	@RequestMapping("/query.do")
	public ModelAndView queryEstimateList(@RequestParam(value="start",required=false) Integer start , 
			@RequestParam(value="limit",required=false) Integer limit){

		PageResult<EvaluationResult> result = hollandManageService.findList(start, limit) ;
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		mv.addObject("data", result.getResult()); 
		mv.addObject("totalCount", result.getTotalCount()) ;
		return mv ;
	}
	
	@RequestMapping("/applyList.do")
	public ModelAndView gotoApplyList(){
		
		return new ModelAndView("manage/holland/applyList") ;
	}
	
	
	
	
}
