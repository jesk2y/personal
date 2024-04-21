package org.jeskey.interceptor;

import java.io.PrintWriter;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final String method = request.getMethod();

		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		String uri = "";

		if(method.equals("GET")){

			if(id != null) {	//이미 로그인이 되어있을 때

				//직전 uri
				Optional<Object> result = Optional.ofNullable(request.getSession().getAttribute("prev_url"));

				if(result.isPresent()) {
					uri = result.toString(); //직전 uri을 session에 기록한다.
				}else {
					uri = "/board/list";	//직전 uri가 없을 경우 홈으로
				}

				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();

		        out.println("<script>alert('이미 로그인 되어있습니다'); history.go(-1);</script>");

				return false;
			}
        }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}



}
