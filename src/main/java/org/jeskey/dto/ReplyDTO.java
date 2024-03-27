package org.jeskey.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

	private long rno;

	@NotEmpty(message = "댓글을 입력해주세요")
	@Size(max = 1000, message = "최대 1000글자까지 입력할 수 있습니다")
	private String content;

	private String user_id;

	private Long pno;

	private char del;

	private LocalDateTime regDate;

	private long bno;

}
