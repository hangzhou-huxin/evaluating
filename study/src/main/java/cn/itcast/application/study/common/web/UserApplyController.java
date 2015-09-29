package cn.itcast.application.study.common.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.application.study.common.domain.ApplyInfo;
import cn.itcast.application.study.common.service.ApplyInfoService;
import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;
import cn.itcast.application.study.manage.holland.dto.EvaluationResultQuery;

@Controller
@RequestMapping("/common")
public class UserApplyController {
	
	@Autowired
	private ApplyInfoService applyInfoService ;
	
	@Autowired  
	private	HttpServletRequest	request;
	
	
	@RequestMapping("/apply.do")
	public ModelAndView execute(@RequestParam(Constant.EVALUATION_ID_PARAM_NAME) String evalId){
		
		Map<String,String> cache = EvaluationUtils.getCacheData(evalId) ;
		String name = cache.get(Constant.USER_NAME_PARAM_NAME) ;
		String qq = cache.get(Constant.QQ_PARAM_NAME) ;
		String view = "apply" ;
		ModelAndView mv = new ModelAndView( view ) ;
		mv.addObject("name", name) ;
		mv.addObject("qq",qq) ;
		mv.addObject(Constant.EVALUATION_ID_PARAM_NAME, evalId) ;
		return mv ;
	}
	
	@RequestMapping("/apply/save.do")
	public ModelAndView save(@ModelAttribute("applyInfo") ApplyInfo applyInfo){
		String ip = request.getRemoteAddr() ;
		applyInfo.setIp(ip);
		applyInfoService.saveApplyInfo(applyInfo);
		String url = "redirect:/manage/holland/viewReport.do?" + Constant.EVALUATION_ID_PARAM_NAME + "=" + applyInfo.getEvId();
		ModelAndView mv = new ModelAndView(url);
		
		return mv ;
	}

}
