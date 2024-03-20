package org.jeskey.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import org.jeskey.mapper.BoardMapper;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CookieUtils {

	private final BoardMapper boardMapper;

	public void viewCountValidation(long bno, HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		boolean isCookie = false;

		//request에 쿠키가 있을 때
		for(int i = 0; cookies != null && i < cookies.length; i++) {

			if(cookies[i].getName().equals("viewCount")) {//viewCount 쿠키가 있을 때

				cookie = cookies[i];

				//쿠키에 현재 게시글 번호가 없을 때
				if(!cookie.getValue().contains("["+ bno + "]")) {

					boardMapper.addCount(bno);
					cookie.setValue(cookie.getValue() + "[" + bno + "]");
				}

				isCookie = true;
				break;
			}
		}

		//request에 쿠키가 없을 때
		if(!isCookie) {
			boardMapper.addCount(bno);	//조회수 증가
			cookie = new Cookie("viewCount", "[" + bno + "]");	//새 쿠키 생성
		}

		// 쿠키 유지시간을 오늘 하루 자정까지로 설정
        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/"); // 모든 경로에서 접근 가능
        cookie.setMaxAge((int) (todayEndSecond - currentSecond));
        response.addCookie(cookie);
	}
}
