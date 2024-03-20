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
	private long rno;
	private String content;
	private String user_id;
	private LocalDateTime regDate;

	private long bno;
}
