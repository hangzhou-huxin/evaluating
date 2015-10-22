package cn.itcast.application.study.evaluation.escape.web;

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
import cn.itcast.application.study.evaluation.utils.RandomUtil;
import cn.itcast.application.study.evaluation.utils.WebUtil;
import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.EscapeCategory;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.domain.EscapeResult;
import cn.itcast.application.study.manage.escape.domain.ScoreSection;
import cn.itcast.application.study.manage.escape.domain.Template;
import cn.itcast.application.study.manage.escape.service.DimensionService;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;
import cn.itcast.application.study.manage.escape.service.EscapeQuestionService;
import cn.itcast.application.study.manage.escape.service.EscapeResultService;
import cn.itcast.application.study.manage.escape.service.ScoreSectionService;
import cn.itcast.application.study.manage.escape.service.TemplateService;

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
	
	@Autowired
	private EscapeResultService escapeResultService ;
	
	@Autowired
	private ScoreSectionService scoreSectionService ;
	
	
	@Autowired
	private TemplateService templateService ;
	
	@RequestMapping("/index.do")
	public ModelAndView index(){
		List<EscapeCategory> categoryList = escapeCategoryService.findForList() ;
		ModelAndView mv = new ModelAndView("escape/index") ;
		mv.addObject("categoryList", categoryList) ;
		return mv ;
	}
	
	
	public ModelAndView apply(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evid){
		ModelAndView mv = new ModelAndView("apply") ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME , evid) ;
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
							 @RequestParam(Constant.USER_NAME_PARAM_NAME) String username,
							 @RequestParam(Constant.QQ_PARAM_NAME) String qq 
							 ) {
		
			String	viewName = "" ;
			ModelAndView mv = new ModelAndView(  ) ;
			//缓存数据
			Map params =  WebUtil.getParamsToMap(request);
			EvaluationUtils.saveStepDataToCache(evid, params);
			
			
			//获取缓存中提交后的测试数据,转测试数据为json
			Map<String,String> cache = EvaluationUtils.getCacheData(evid) ;
			String cacheJson = EvaluationUtils.cacheDataToJSON(cache , new HashMap()) ;
			
			
			viewName = "escape/step" ;
			
			System.out.println(viewName);
			List<EscapeQuestion> list = escapeQuestionService.findForListWithOptions(categoryId) ;
			//渲染页面
			mv.setViewName(viewName);
			mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evid) ;
			mv.addObject("cache",cacheJson) ;
			mv.addObject("questions", JsonUtils.objectToJson(list)) ;
			mv.addObject( "categoryId", categoryId) ;
			mv.addObject(Constant.USER_NAME_PARAM_NAME,username) ;
			mv.addObject(Constant.QQ_PARAM_NAME, qq) ;
			
			//mv.addObject("step", step) ;
			return  mv ;
		
	}
	
	
	@RequestMapping("/finish.do")
	public ModelAndView finish(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evid,
		 						@RequestParam("categoryId") Integer categoryId){
		
		    
		   ModelAndView mv = new ModelAndView() ;
		   String ip = request.getRemoteAddr() ;
		   
		   Map<String,String> data = WebUtil.getParamsToMap(request);
		   String userName = data.get(Constant.USER_NAME_PARAM_NAME);
		   String qq = data.get(Constant.QQ_PARAM_NAME);
		   
		   Map<String,Integer> result = EvaluationUtils.getEscapeEvaluationResult(data) ;
		   
		   EscapeResult escapeResult = new EscapeResult() ;
		   escapeResult.setCategoryId(categoryId);
		   escapeResult.setEvalId(evid);
		   escapeResult.setQq(qq);
		   escapeResult.setUserName(userName);
		   escapeResult.setIp(ip);
		   Integer totalScore = result.get(Constant.ESCAPE_TOTAL_SCORE) ;
		   escapeResult.setTotalScore(totalScore);
		   String cacheJson = EvaluationUtils.cacheDataToJSON(data ,Constant.ESCAPE_PREFIX) ;
		   escapeResult.setContent(cacheJson);
		   escapeResultService.save(escapeResult);
		   mv.addObject("result",  result) ;
		   
		   String viewName = "escape/default-report";
		   
		   //获取相应的分值段
		   ScoreSection  myScoreSection = null ;
		   List<ScoreSection> scoreSectionList = scoreSectionService.findForList(categoryId) ;
		   for(ScoreSection scoreSection : scoreSectionList){
			   Integer lowerValue = scoreSection.getLowerValue() ;
			   Integer upperValue = scoreSection.getUpperValue() ;
			   if( totalScore >= lowerValue && totalScore <= upperValue){
				   myScoreSection = scoreSection ;
				   break ;
			   }
		   }
		   mv.addObject("scoreSection", myScoreSection) ;
		   
		   //获取相应的模板
		   Map<String,String> replateMap = new HashMap<String,String>() ;
		   replateMap.put("totalScore", result.get(Constant.ESCAPE_TOTAL_SCORE).toString()) ;
		   
		   if(myScoreSection != null){
			   List<Template> templateList = templateService.findForList(myScoreSection.getId()) ;
			   if(templateList != null && templateList.size()>0){
				   int size = templateList.size() ;
				   int index = RandomUtil.getNumber(size) ;
				   Template template = templateList.get(index) ;
				   if(template != null){
					   String templateContent = template.getContent() ;
					   String replatedContent = EvaluationUtils.replateTemplateVars(Constant.TEMPLATE_VARS, templateContent, replateMap) ;
					   viewName = "escape/report" ;
					   mv.addObject("templateContent", replatedContent) ;
				   }
			   }
			   
		   }
		   
		   mv.setViewName(viewName);
		   return mv ;
	}
	
	
	
	
	

}
