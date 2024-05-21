package org.jeskey.interceptor;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class BlockLoginUserInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final String method = request.getMethod();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		if(method.equals("GET") && id != null){
			//이미 로그인이 되어있을 때
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

	        out.println("<script>alert('이미 로그인 되어있습니다'); history.go(-1);</script>");

			return false;
        }
		return true;
	}
}
