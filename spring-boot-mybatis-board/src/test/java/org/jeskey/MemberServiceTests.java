package org.jeskey;

import org.jeskey.dto.MemberDTO;
import org.jeskey.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTests {

	@Autowired
	private MemberService memberService;

	@Test
	public void insertMember() {

		for(int i = 2; i<10; i++){

			MemberDTO dto = MemberDTO.builder()
					.user_id("user"+i)
					.password("11111")
					.email("aaa"+i+"@gmail.net").build();

			memberService.insertMember(dto);
		}
	}
}
