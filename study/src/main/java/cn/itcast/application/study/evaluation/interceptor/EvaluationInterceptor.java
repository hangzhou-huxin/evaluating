package cn.itcast.application.study.evaluation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.application.study.evaluation.core.Constant;
import cn.itcast.application.study.evaluation.core.EvaluationUtils;

public class EvaluationInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String evid = (String)request.getParameter(Constant.EVALUATION_ID_PARAM_NAME) ;
		if(StringUtils.isEmpty(evid)){
			evid = (String)request.getAttribute(Constant.EVALUATION_ID_PARAM_NAME) ;
		} 
		if( StringUtils.isEmpty(evid) ||!EvaluationUtils.validateEvalId(evid)){
			//进入测试页面，产生测试编号
			String evalId = java.util.UUID.randomUUID().toString() ;
			System.out.println("evalId=" +evalId);
			EvaluationUtils.putEvalId(evalId);
			request.setAttribute(Constant.EVALUATION_ID_PARAM_NAME, evalId);
			request.getRequestDispatcher("/WEB-INF/views/holland/main.jsp").forward(request, response);  
			return false ;
		}
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
