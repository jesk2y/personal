package org.jeskey.interceptor;

import java.io.PrintWriter;

import org.jeskey.service.BoardService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckAuthorityInterceptor implements HandlerInterceptor{

	private final BoardService boardService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		Long bno = Long.parseLong(request.getParameter("bno"));	//bno 파라미터

		if(bno != null) {//bno 파라미터가 존재할 때

			if(!id.equals(boardService.getOne(bno).getUser_id())) {
				//글 작성자와 로그인 사용자가 일치하지 않을 때
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

		        out.println("<script>alert('권한이 없습니다'); history.go(-1);</script>");

				return false;
			};
		}
		return true;
	}
}
