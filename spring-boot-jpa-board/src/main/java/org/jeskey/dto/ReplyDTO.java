package org.jeskey.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

	private Long rno;

	@NotNull
	private Long bno;

	@NotEmpty(message = "댓글을 입력해주세요")
	@Size(max = 1000, message = "최대 1000글자까지 입력할 수 있습니다")
	private String replyText;

	@JsonFormat(pattern="MM-dd HH:mm")
	private LocalDateTime regdate;

    @JsonIgnore
    private LocalDateTime updatedate;
}
