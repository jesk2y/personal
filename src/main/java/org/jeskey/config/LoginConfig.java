package org.jeskey.config;

import org.jeskey.interceptor.BlockLoginUserInterceptor;
import org.jeskey.interceptor.BlockUnloginUserInterceptor;
import org.jeskey.interceptor.CheckAuthorityInterceptor;
import org.jeskey.interceptor.SaveURLInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LoginConfig implements WebMvcConfigurer{

	private final SaveURLInterceptor urlInterceptor;
	private final CheckAuthorityInterceptor cAuthorityInterceptor;
	private final BlockLoginUserInterceptor bLoginUserInterceptor;
	private final BlockUnloginUserInterceptor bUnloginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		//세션에 접속한 url 저장
		registry.addInterceptor(urlInterceptor)
		.addPathPatterns("/board/list", "/board/content");

		//로그인 사용자 접근 막음
		registry.addInterceptor(bLoginUserInterceptor)
				.addPathPatterns("/login", "/signup");

		//비로그인 사용자 접근 막음
		registry.addInterceptor(bUnloginInterceptor)
		.addPathPatterns("/changepw", "/memberInfo", "/board/register");

		//글 작성자가 아닐 시 접근 막음
		registry.addInterceptor(cAuthorityInterceptor)
		.addPathPatterns("/board/update", "/board/delete");
	}
}
