package org.jeskey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
class BasicBoardJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicBoardJpaApplication.class, args);
	}

}
