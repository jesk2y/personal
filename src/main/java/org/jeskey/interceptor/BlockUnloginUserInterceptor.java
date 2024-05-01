package org.jeskey.interceptor;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class BlockUnloginUserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String path = request.getServletPath();

		//로그인 안하고 특정 링크에 접근할 때
		if(path.equals("/board/register") || path.equals("/memberInfo") || path.equals("/changepw")) {
			if(id == null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

		        out.println("<script>alert('로그인이 필요합니다'); history.go(-1);</script>");

				return false;
			}
		}
		return true;
	}
}
