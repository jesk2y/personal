package org.jeskey.common;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	//비밀번호 암호화
	public String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	//비밀번호 일치 확인
	public boolean ismatch(String rawPassword, String hashedPassword) {
		return BCrypt.checkpw(rawPassword, hashedPassword);
	}
}