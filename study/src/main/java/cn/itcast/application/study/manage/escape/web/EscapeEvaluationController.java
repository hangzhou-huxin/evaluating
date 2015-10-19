package cn.itcast.application.study.manage.escape.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;
import cn.itcast.application.study.evaluation.utils.JsonUtils;
import cn.itcast.application.study.evaluation.utils.WebUtil;
import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.EscapeCategory;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.service.DimensionService;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;
import cn.itcast.application.study.manage.escape.service.EscapeQuestionService;

@Controller
@RequestMapping("/escape/evaluation")
public class EscapeEvaluationController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	
	@Autowired
	private EscapeQuestionService escapeQuestionService ;
	
	@Autowired
	private EscapeCategoryService escapeCategoryService ;
	
	@RequestMapping("/index.do")
	public ModelAndView index(){
		List<EscapeCategory> categoryList = escapeCategoryService.findForList() ;
		ModelAndView mv = new ModelAndView("escape/index") ;
		mv.addObject("categoryList", categoryList) ;
		return mv ;
	}
	
	
	
	@RequestMapping("/main.do")
	public ModelAndView main(@RequestParam("id") Integer id){
		//产生一个评测编号
		String evid = java.util.UUID.randomUUID().toString() ;
		//将评测编号放入缓存	
		EvaluationUtils.putEvalId(evid);
			
		//根据categoryId获取category信息
		EscapeCategory category = escapeCategoryService.findById(id) ;
		ModelAndView mv = new ModelAndView("escape/main") ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evid) ;
		mv.addObject("category", category) ;
		return mv ;
	}
	
	
	@RequestMapping("/step.do")
	public ModelAndView step(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evid,
							 @RequestParam("categoryId") Integer categoryId,
							 @RequestParam("step") Integer step ,
							 @RequestParam(value="stepCount",required=false) Integer stepCount ,
							 @RequestParam(value=Constant.PREVIOUS_STEP_PARAM_NAME,required=false) String previous ,
							 @RequestParam(value=Constant.NEXT_STEP_PARAM_NAME,required=false) String next 
							 ) {
		
			Integer pageNo = 0;
			String	viewName = "" ;
			ModelAndView mv = new ModelAndView(  ) ;
			//缓存数据
			Map params =  WebUtil.getParamsToMap(request);
			EvaluationUtils.saveStepDataToCache(evid, params);
			
			
			//获取缓存中提交后的测试数据,转测试数据为json
			Map<String,String> cache = EvaluationUtils.getCacheData(evid) ;
			String cacheJson = EvaluationUtils.cacheDataToJSON(cache , null) ;
			
			if( next != null && !StringUtils.isEmpty(next)){
				step = step++ ;
				pageNo = step ;
			}else if( previous != null && !StringUtils.isEmpty(previous)){
				step = step-- ;
				pageNo = step ;
			}else{
				pageNo = 1 ;
			}
			
			if( stepCount == null){
				Integer count = escapeQuestionService.findForPageListCount(categoryId) ;
				stepCount = count/10 ;
				if(count % 10 > 0){
					stepCount = stepCount + 1 ;
				}
			}
			
			//如果下一页是最后一页，则进入完成页面
			if( pageNo >= stepCount){
				//进入完成页面
				viewName = "escape/finish" ;
			}else{
				//进入步聚页面
				viewName = "escape/step" ;
			}
			System.out.println(viewName);
			List<EscapeQuestion> list = escapeQuestionService.findForPageList(categoryId, pageNo) ;
			//渲染页面
			mv.setViewName(viewName);
			mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evid) ;
			mv.addObject("cache",cacheJson) ;
			mv.addObject("questions", JsonUtils.objectToJson(list)) ;
			if(step < 1){
				step = 1 ;
			}
			mv.addObject("step", step) ;
			return  mv ;
		
	}
	
	
	
	
	

}
