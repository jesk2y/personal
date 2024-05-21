package org.jeskey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BasicBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicBoardApplication.class, args);
	}
}
