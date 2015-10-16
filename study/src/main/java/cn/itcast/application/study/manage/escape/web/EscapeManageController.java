package cn.itcast.application.study.manage.escape.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;
import cn.itcast.application.study.manage.escape.domain.EscapeCategory;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;
import cn.itcast.application.study.manage.escape.service.EscapeQuestionService;
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.dto.EvaluationResultQuery;
import cn.itcast.application.study.manage.holland.service.HollandManageService;
import cn.itcast.application.study.utils.Page;
import cn.itcast.application.study.utils.PageResult;



@Controller
@RequestMapping("/manage/escape")
public class EscapeManageController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	
	@Autowired
	private EscapeCategoryService escapeCategoryService ;
	
	@Autowired
	private EscapeQuestionService escapeQuestionService ;
	
	@RequestMapping("/config.do")
	public ModelAndView main(){
		
		return new ModelAndView("manage/escape/config") ;
	}
	
	
	@RequestMapping("/category/save.do")
	public ModelAndView saveCategory(@ModelAttribute("category") EscapeCategory category){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		
		try{
			escapeCategoryService.save(category);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
		return mv ;
	}
	
	
	@RequestMapping("/questions/save.do")
	public ModelAndView saveQuestion(@ModelAttribute("question") EscapeQuestion question){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		
		try{
			escapeQuestionService.save(question);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
		return mv ;
	}
	
	
	@RequestMapping("/category/list.do")
	public ModelAndView categoryList(){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		List<EscapeCategory> list = escapeCategoryService.findForList() ;
		mv.addObject("data", list); 
		mv.addObject("totalCount", list.size()) ;
		return mv ;
	}
	
	
	@RequestMapping("/questions/list.do")
	public ModelAndView questionList(){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		List<EscapeQuestion> list = escapeQuestionService.findForList() ;
		mv.addObject("data", list); 
		mv.addObject("totalCount", list.size()) ;
		return mv ;
	}
	
	
	@RequestMapping("/questions/delete.do")
	public ModelAndView deleteQuestion(@RequestParam("id") Integer id ){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		try{
			escapeQuestionService.delete(id);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
		return mv ;
	}
	
	
	@RequestMapping("/category/editQuestions.do")
	public ModelAndView editQuestions(){
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		EscapeCategory category = escapeCategoryService.findById( categoryId) ;
		ModelAndView mv = new ModelAndView("manage/escape/questions") ;
		mv.addObject( "category", category) ;
		return mv ;
	}
	
	
	
}