package cn.itcast.application.study.manage.escape.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.itcast.application.study.evaluation.escape.domain.EscapeApplyInfo;
import cn.itcast.application.study.evaluation.escape.service.EscapeApplyInfoService;
import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.EscapeCategory;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.service.DimensionService;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;

@Controller
@RequestMapping("/manage/escape/apply")
public class ApplyController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	

	@Autowired
	private EscapeApplyInfoService escapeApplyInfoService ;
	
	
	@RequestMapping("/main.do")
	public ModelAndView main(){
		ModelAndView mv = new ModelAndView("manage/escape/apply") ;
		return mv ;
	}
	
	
	@RequestMapping("/list.do")
	public ModelAndView list(@RequestParam("categoryId") Integer categoryId,
			@RequestParam("start") Integer start,
			@RequestParam("limit") Integer limit){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		List<EscapeApplyInfo> list = escapeApplyInfoService.findByCategoryId(categoryId, start, limit) ;
		mv.addObject("data", list); 
		mv.addObject("totalCount", list.size()) ;
		return mv ;
	}
	
	
	

}
