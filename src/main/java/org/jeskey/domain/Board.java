package org.jeskey.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private String title;
	private String content;
	private String password;
	private LocalDateTime regdate;
	
}
