package org.jeskey.domain;

import java.time.LocalDateTime;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

	private String user_id;
	private String email;
	private String password;
	private char is_del;
	private LocalDateTime regdate;

}
