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
import cn.itcast.application.study.evaluation.utils.JsonUtils;
import cn.itcast.application.study.evaluation.utils.RandomUtil;
import cn.itcast.application.study.evaluation.utils.WebUtil;
import cn.itcast.application.study.manage.escape.domain.EscapeCategory;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestionOption;
import cn.itcast.application.study.manage.escape.domain.EscapeResult;
import cn.itcast.application.study.manage.escape.domain.ScoreSection;
import cn.itcast.application.study.manage.escape.domain.Template;
import cn.itcast.application.study.manage.escape.dto.EscapeResultQuery;
import cn.itcast.application.study.manage.escape.service.EscapeCategoryService;
import cn.itcast.application.study.manage.escape.service.EscapeQuestionOptionService;
import cn.itcast.application.study.manage.escape.service.EscapeQuestionService;
import cn.itcast.application.study.manage.escape.service.EscapeResultService;
import cn.itcast.application.study.manage.escape.service.ScoreSectionService;
import cn.itcast.application.study.manage.escape.service.TemplateService;
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.dto.EvaluationResultQuery;
import cn.itcast.application.study.manage.holland.service.HollandManageService;
import cn.itcast.application.study.utils.Page;
import cn.itcast.application.study.utils.PageResult;



@Controller
@RequestMapping("/manage/escape/result")
public class EscapeResultController {
	
	@Autowired  
	private	HttpServletRequest	request;
	
	
	@Autowired
	private MappingJackson2JsonView jsonView  ;
	
	
	@Autowired
	private ScoreSectionService scoreSectionService ;
	
	
	
	@Autowired
	private EscapeQuestionService escapeQuestionService ;
	
	
	@Autowired
	private EscapeCategoryService escapeCategoryService ;
	
	
	@Autowired
	private EscapeResultService escapeResultService ;
	

	@Autowired
	private TemplateService templateService ;
	
	
	@RequestMapping("/viewReport.do")
	public ModelAndView viewReport(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evid,
				@RequestParam("categoryId") Integer categoryId){
		
		 EscapeResult escapeResult = escapeResultService.findByEvalId(evid) ;
		 ModelAndView mv = null ;
		   String ip = request.getRemoteAddr() ;
		   
		   Map<String,String> data = WebUtil.getParamsToMap(request);
		   String userName = data.get(Constant.USER_NAME_PARAM_NAME);
		   String qq = data.get(Constant.QQ_PARAM_NAME);
		   
		   Map<String,Integer> result = EvaluationUtils.getEscapeEvaluationResult(data) ;
		   
		   
		  
		   
		   //获取相应的分值段
		   ScoreSection  myScoreSection = null ;
		   List<ScoreSection> scoreSectionList = scoreSectionService.findForList(categoryId) ;
		   Integer totalScore = result.get(Constant.ESCAPE_TOTAL_SCORE) ;
		   for(ScoreSection scoreSection : scoreSectionList){
			   Integer lowerValue = scoreSection.getLowerValue() ;
			   Integer upperValue = scoreSection.getUpperValue() ;
			   if( totalScore >= lowerValue && totalScore <= upperValue){
				   myScoreSection = scoreSection ;
				   break ;
			   }
		   }
		   
		   
		   //获取相应的模板
		  Map<String,String> replateMap = new HashMap<String,String>() ;
		   replateMap.put("totalScore", result.get(Constant.ESCAPE_TOTAL_SCORE).toString()) ;
		   
		   Template template = templateService.findById( escapeResult.getTemplateId()) ;
		   if(template != null){
			   String templateContent = template.getContent() ;
			   String replatedContent = EvaluationUtils.replateTemplateVars(Constant.TEMPLATE_VARS, templateContent, replateMap) ;
			   mv = new ModelAndView( "escape/report" );
			   mv.addObject("result",  result) ;
			   mv.addObject("scoreSection", myScoreSection) ;
			  mv.addObject("templateContent", replatedContent) ;
		   }else{
			   mv = new ModelAndView("escape/default-report");
			   mv.addObject("result",  result) ;
		   }
		
		   return mv ;
	}
	
	
	@RequestMapping("/viewDetail.do")
	public ModelAndView viewDetail(@RequestParam("id") Integer id){

		String	viewName = "manage/escape/step" ;
		ModelAndView mv = new ModelAndView( viewName ) ;
		
		EscapeResult escapeResult = escapeResultService.findById(id) ;
		
		Integer categoryId = escapeResult.getCategoryId() ;
		EscapeCategory category = escapeCategoryService.findById(categoryId) ;
		
		String evid = escapeResult.getEvalId() ;
		String username = escapeResult.getUserName() ;
		String qq = escapeResult.getQq() ;
		String data = escapeResult.getContent() ;
		
		
		System.out.println(viewName);
		List<EscapeQuestion> list = escapeQuestionService.findForListWithOptions(categoryId) ;
		//渲染页面
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evid) ;
		mv.addObject("cache",data) ;
		mv.addObject("questions", JsonUtils.objectToJson(list)) ;
		mv.addObject( "category", category) ;
		mv.addObject(Constant.USER_NAME_PARAM_NAME,username) ;
		mv.addObject(Constant.QQ_PARAM_NAME, qq) ;
		
		//mv.addObject("step", step) ;
		return  mv ;
		
	}
	
	
	
}
