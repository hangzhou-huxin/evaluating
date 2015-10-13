package cn.itcast.application.study.manage.holland.web;

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
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.dto.EvaluationResultQuery;
import cn.itcast.application.study.manage.holland.service.HollandManageService;
import cn.itcast.application.study.utils.Page;
import cn.itcast.application.study.utils.PageResult;



@Controller
@RequestMapping("/manage/holland")
public class HollandManageController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	@Autowired
	private HollandManageService hollandManageService  ;
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	
	

	
	@RequestMapping("/list.do")
	public ModelAndView gotoList(){
		
		return new ModelAndView("manage/holland/list") ;
	}
	
	
	@RequestMapping("/query.do")
	public ModelAndView queryEstimateList(@ModelAttribute("query") EvaluationResultQuery query, 
			@RequestParam(value="start",required=false) Integer start , 
			@RequestParam(value="limit",required=false) Integer limit){
		System.out.println(query);
		PageResult<EvaluationResult> result = hollandManageService.findList(query) ;
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		mv.addObject("data", result.getResult()); 
		mv.addObject("totalCount", result.getTotalCount()) ;
		return mv ;
	}
	
	
	@RequestMapping("/queryForApply.do")
	public ModelAndView queryApplyList(){
		
		List<EvaluationResult> list = hollandManageService.findListForApply() ;
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		mv.addObject("data",list); 
		return mv ;
	}
	
	
	@RequestMapping("/applyList.do")
	public ModelAndView gotoApplyList(){
		
		
		return new ModelAndView("manage/holland/applyList") ;
	}
	
	
	
	
	
	@RequestMapping("/viewReport.do")
	public ModelAndView viewReport( @RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId ){
		
		ModelAndView mv = new ModelAndView("manage/holland/report") ;
		EvaluationResult result = hollandManageService.findByEvalId(evalId) ;
		Map<String,Integer> typeAndValue = new HashMap() ;
		typeAndValue.put("r", result.getrValue()) ;
		typeAndValue.put("s", result.getsValue()) ;
		typeAndValue.put("i", result.getiValue()) ;
		typeAndValue.put("a", result.getaValue()) ;
		typeAndValue.put("e", result.geteValue()) ;
		typeAndValue.put("c", result.getcValue()) ;
		String newResult = EvaluationUtils.getOrderedResult(typeAndValue) ;
		String commonFeature = Constant.commonFeatureMap.get(newResult.toLowerCase()) ;
		String characterFeature = Constant.characterFeatureMap.get(newResult.toLowerCase()) ;
		mv.addObject("commonFeature",commonFeature ) ;
		mv.addObject("characterFeature",characterFeature) ;
		mv.addObject("orderedResult", newResult) ;
		mv.addObject( "result"	, typeAndValue) ;
		return mv ;
	}
	
	
	@RequestMapping("/gotoStep.do")
	public ModelAndView gotoStep( @RequestParam("step") Integer step ,
								  @RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId ,
								  @RequestParam(value=Constant.PREVIOUS_STEP_PARAM_NAME,required=false) String previous,
								  @RequestParam(value=Constant.NEXT_STEP_PARAM_NAME,required=false) String next){
		if(step == null){
			step = Integer.parseInt((String)request.getAttribute("step")) ;
		}
		String view  = "manage/holland/step" + step ;;
		
		EvaluationResult result = hollandManageService.findByEvalId(evalId) ;
		if(next != null)
			step++ ;
		else if(previous != null)
			step-- ;

		ModelAndView mv = new ModelAndView(view) ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		mv.addObject("cache",result.getContent()) ;
		mv.addObject("step", step) ;
		return mv ;
	}
	
	
	@RequestMapping("/delete.do")
	public ModelAndView delete(@RequestParam("id") Integer id ){
		
		String msg = null ;
		try{
			hollandManageService.deleteResult(id);
			msg = "删除成功" ;
		}catch(Exception e){
			msg = e.getMessage() ;
		}
		
		ModelAndView mv = new ModelAndView() ;
		mv.setView(jsonView);
		return mv;
	}
	
	
	
	
}
