package org.jeskey.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
	private Long rno;
	private String content;
	private String user_id;
	private LocalDateTime regDate;
	private char del;
	private Long pno;

	private Long bno;
}
