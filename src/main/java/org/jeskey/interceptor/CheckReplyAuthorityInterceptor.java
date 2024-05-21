package org.jeskey.interceptor;

import org.jeskey.service.ReplyService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckReplyAuthorityInterceptor implements HandlerInterceptor {

	private final ReplyService replyService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		final String method = request.getMethod();

		if(method.equals("POST")) {
			if (id == null) { // 댓글 등록
				throw new IllegalAccessException("로그인 사용자만 댓글 작성이 가능합니다");
			}
			return true;
		}

		if (method.equals("DELETE")) { // 댓글 삭제

			String getPath = request.getRequestURI(); // reply/rno
			String rnoStr = getPath.split("/")[getPath.split("/").length - 1]; // rno
			Long rno = Long.parseLong(rnoStr);

			  if(!id.equals(replyService.getOneReply(rno).getUser_id())) { //댓글 작성자와 로그인 사용자가 일치하지 않을 때
				  response.setContentType("text/html; charset=UTF-8");
				  throw new IllegalAccessException("권한이 없습니다");
			  };
		}
		return true;
	}
}
