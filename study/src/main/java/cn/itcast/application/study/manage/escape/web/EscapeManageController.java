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
import cn.itcast.application.study.manage.escape.domain.EscapeQuestionOption;
import cn.itcast.application.study.manage.escape.domain.EscapeResult;
import cn.itcast.application.study.manage.escape.dto.EscapeResultQuery;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;
import cn.itcast.application.study.manage.escape.service.EscapeQuestionOptionService;
import cn.itcast.application.study.manage.escape.service.EscapeQuestionService;
import cn.itcast.application.study.manage.escape.service.EscapeResultService;
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
	
	@Autowired
	private EscapeQuestionOptionService escapeQuestionOptionService ;
	
	
	@Autowired
	private EscapeResultService escapeResultService ;
	
	@RequestMapping("/config.do")
	public ModelAndView main(){
		
		return new ModelAndView("manage/escape/config") ;
	}
	
	
	@RequestMapping("/list.do")
	public ModelAndView gotoList(){
		
		return new ModelAndView("manage/escape/list") ;
	}
	
	
	@RequestMapping("/query.do")
	public ModelAndView query(@ModelAttribute("query") EscapeResultQuery query){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		PageResult<EscapeResult> result = escapeResultService.findPageList(query) ;
		mv.addObject("data", result.getResult()); 
		mv.addObject("totalCount", result.getTotalCount()) ;
		return mv ;
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
	
	
	
	
	
	@RequestMapping("/options/save.do")
	public ModelAndView saveOption(@ModelAttribute("option") EscapeQuestionOption option){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		
		try{
			escapeQuestionOptionService.save(option);
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
	public ModelAndView questionList(@RequestParam("categoryId") Integer categoryId){
		
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		List<EscapeQuestion> list = escapeQuestionService.findForList( categoryId) ;
		mv.addObject("data", list); 
		mv.addObject("totalCount", list.size()) ;
		return mv ;
	}
	
	@RequestMapping("/options/list.do")
	public ModelAndView optoinList(@RequestParam("questionId") Integer questionId){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		List<EscapeQuestionOption> list = escapeQuestionOptionService.findForList(questionId) ;
		mv.addObject("data", list); 
		mv.addObject("totalCount", list.size()) ;
		return mv ;
	}
	
	
	@RequestMapping("/category/delete.do")
	public ModelAndView deleteCategory(@RequestParam("id") Integer id ){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		try{
			escapeCategoryService.delete(id);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
		return mv ;
	}
	
	
	@RequestMapping("/result/delete.do")
	public ModelAndView deleteResult(@RequestParam("id") Integer id ){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		try{
			escapeResultService.delete(id);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
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
	
	
	@RequestMapping("/options/delete.do")
	public ModelAndView deleteOption(@RequestParam("id") Integer id ){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		try{
			escapeQuestionOptionService.delete(id);
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
