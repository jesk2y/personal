package org.jeskey;

import org.jeskey.dto.PageDTO;
import org.jeskey.dto.ReplyDTO;
import org.jeskey.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyServiceTests {

	@Autowired
	private ReplyService replyService;

	@Test
	public void getListWithPage() {

		PageDTO page = new PageDTO();
		page.setReply();

		//page.setPage(1);
		page.setBno(1236L);

		replyService.getListReply(page).stream().forEach(dto -> log.info(dto));
	}

	@Test
	public void insert() {
		ReplyDTO dto = ReplyDTO.builder()
				.bno(1153L)
				.content("댓글 등록 테스트")
				.user_id("user1").build();

		replyService.insertReply(dto);
	}
}
