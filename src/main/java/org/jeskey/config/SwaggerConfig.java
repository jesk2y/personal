package org.jeskey.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(title = "기본 게시판 REST 설정",
                description = "기본 게시판 REST 설정",
                version = "v1"))
@Configuration
public class SwaggerConfig {

	/*
	 * @Bean public OpenAPI customSwagger() {
	 *
	 * return new OpenAPI().addServersItem(new Server().url("/"));
	 *
	 * }
	 */
}
