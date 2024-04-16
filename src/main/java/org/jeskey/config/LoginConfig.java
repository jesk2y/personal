package org.jeskey.config;

import org.jeskey.interceptor.CustomInterceptor;
import org.jeskey.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LoginConfig implements WebMvcConfigurer{

	private final LoginInterceptor loginInterceptor;
	private final CustomInterceptor customInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(customInterceptor)
		.addPathPatterns("/board/**", "/member/**");

		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/login");


	}
}
