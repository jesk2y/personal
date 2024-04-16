package org.jeskey.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

	@NotEmpty(message = "아이디를 입력해주세요")
	@Size(min = 5, message = "아이디는 최소 5글자 이상이어야 합니다.")
	@Size(max = 100, message = "아이디가 너무 깁니다.")
	private String user_id;

	@NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
	@Size(max = 100, message = "이메일이 너무 깁니다.")
	private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	@Size(max = 100, message = "비밀번호가 너무 깁니다.")
	private String password;

	private char is_del;
	private LocalDateTime regdate;

	public void encodePassword(String rawPassword) {
		this.password = rawPassword;
	}
}
