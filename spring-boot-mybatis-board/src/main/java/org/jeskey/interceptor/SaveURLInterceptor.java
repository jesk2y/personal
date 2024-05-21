package org.jeskey.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SaveURLInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String path = request.getServletPath();

        String query = request.getQueryString(); //파라미터가 있는지 없는지

        HttpSession httpSession = request.getSession();

        if(query != null){ //주소가 board?no=34&page=33
            httpSession.setAttribute("prev_url", path+"?"+query);

        }else{ //board
            httpSession.setAttribute("prev_url", path);
        }
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {


	}
}
