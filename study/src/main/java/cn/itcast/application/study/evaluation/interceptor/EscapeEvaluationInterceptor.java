package cn.itcast.application.study.evaluation.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;

public class EscapeEvaluationInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("------------------escape---------------------");
		String evid = (String)request.getParameter(Constant.EVALUATION_ID_PARAM_NAME) ;
		if(evid==null || StringUtils.isEmpty(evid)){
			evid = (String)request.getAttribute(Constant.EVALUATION_ID_PARAM_NAME) ;
		} 
		if(evid==null || StringUtils.isEmpty(evid) ||!EvaluationUtils.validateEvalId(evid)){
			//进入测试页面，产生测试编号
			String evalId = java.util.UUID.randomUUID().toString() ;
			EvaluationUtils.putEvalId(evalId);
			request.setAttribute(Constant.EVALUATION_ID_PARAM_NAME, evalId);
			//System.out.println(request.getHeader("referer") );
			//request.setAttribute("errMsg",  "评测失效，请重新进行测试");
			request.getRequestDispatcher("/WEB-INF/views/escape/main.jsp").forward(request, response);  
			return false ;
		}
		
		//获取缓存数据
		Map<String,String> cache = EvaluationUtils.getCacheData(evid )  ;
		Map<String,String> data = new HashMap<String,String>() ;
		String userName = (String)request.getParameter(Constant.USER_NAME_PARAM_NAME)  ;
		if( userName==null || StringUtils.isEmpty(userName)){
			userName = (String)request.getAttribute(Constant.USER_NAME_PARAM_NAME) ;
		}
		if( userName==null || StringUtils.isEmpty(userName) ){
			if(cache.get(Constant.USER_NAME_PARAM_NAME) == null){
				request.setAttribute("errMsg",  "请填写完整");
				request.getRequestDispatcher("/WEB-INF/views/escape/index.jsp").forward(request, response);  
				return false ;
			}
		}
		data.put(Constant.USER_NAME_PARAM_NAME, userName) ;
		
		String qq = (String)request.getParameter(Constant.QQ_PARAM_NAME)  ;
		if( qq==null || StringUtils.isEmpty(qq)){
			qq = (String)request.getAttribute(Constant.QQ_PARAM_NAME) ;
		}
		if( qq==null || StringUtils.isEmpty(qq) ){
			if( cache.get(Constant.QQ_PARAM_NAME) == null ){
				request.setAttribute("errMsg",  "请填写完整");
				request.getRequestDispatcher("/WEB-INF/views/escape/index.jsp").forward(request, response);  
				return false ;
			}
		}
		data.put(Constant.QQ_PARAM_NAME, qq) ;
		EvaluationUtils.saveStepDataToCache(evid, data);
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
