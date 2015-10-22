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

import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.EscapeCategory;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.domain.ScoreSection;
import cn.itcast.application.study.manage.escape.domain.Template;
import cn.itcast.application.study.manage.escape.service.DimensionService;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;
import cn.itcast.application.study.manage.escape.service.ScoreSectionService;
import cn.itcast.application.study.manage.escape.service.TemplateService;

@Controller
@RequestMapping("/manage/escape/template")
public class TemplateController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	
	@Autowired
	private TemplateService templateService ;
	
	@Autowired
	private ScoreSectionService scoreSectionService ;
	
	
	@RequestMapping("/main.do")
	public ModelAndView main(@RequestParam("id") Integer scoreSectionId){
		ScoreSection scoreSection = scoreSectionService.findById(scoreSectionId) ;
		ModelAndView mv = new ModelAndView("manage/escape/template") ;
		mv.addObject("scoreSection", scoreSection) ;
		return mv ;
	}
	
	
	@RequestMapping("/list.do")
	public ModelAndView list(@RequestParam("scoreSectionId") Integer scoreSectionId){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		List<Template> list = templateService.findForList(scoreSectionId) ;
		mv.addObject("data", list); 
		mv.addObject("totalCount", list.size()) ;
		return mv ;
	}
	
	
	
	@RequestMapping("/save.do")
	public ModelAndView save(@ModelAttribute("template") Template template){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		
		try{
			templateService.save(template);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
		return mv ;
	}
	
	
	@RequestMapping("/delete.do")
	public ModelAndView deleteCategory(@RequestParam("id") Integer id ){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		try{
			templateService.delete(id);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
		return mv ;
	}
	
	
	
	

}
