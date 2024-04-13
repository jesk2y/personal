package org.jeskey.common;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	public String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean ismatch(String rawPassword, String encodedPassword) {
		return BCrypt.checkpw(rawPassword, encodedPassword);
	}

}