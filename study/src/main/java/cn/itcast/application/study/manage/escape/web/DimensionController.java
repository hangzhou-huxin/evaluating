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
import cn.itcast.application.study.manage.escape.service.DimensionService;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;

@Controller
@RequestMapping("/manage/escape/dimension")
public class DimensionController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	
	@Autowired
	private DimensionService dimensionService ;
	
	@Autowired
	private EscapeCategoryService escapeCategoryService ;
	
	
	@RequestMapping("/main.do")
	public ModelAndView main(){
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId")) ;
		EscapeCategory category = escapeCategoryService.findById(categoryId) ;
		ModelAndView mv = new ModelAndView("manage/escape/dimension") ;
		mv.addObject("category", category) ;
		return mv ;
	}
	
	
	@RequestMapping("/list.do")
	public ModelAndView list(@RequestParam("categoryId") Integer categoryId){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		List<Dimension> list = dimensionService.findForList(categoryId) ;
		mv.addObject("data", list); 
		mv.addObject("totalCount", list.size()) ;
		return mv ;
	}
	
	
	@RequestMapping("/save.do")
	public ModelAndView save(@ModelAttribute("dimension") Dimension dimension){
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		
		try{
			dimensionService.save(dimension);
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
			dimensionService.delete(id);
			mv.addObject("success", true); 
			mv.addObject("msg", "操作成功") ;
		}catch(Exception e){
			mv.addObject("success", false); 
			mv.addObject("msg", e.getMessage()) ;
		}
		return mv ;
	}
	
	
	
	

}
