package org.jeskey;

import org.jeskey.domain.Reply;
import org.jeskey.dto.PageDTO;
import org.jeskey.mapper.ReplyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper replyMapper;

	@Test
	public void getListWithPage() {

		PageDTO page = new PageDTO();
		page.setReply();

		//page.setPage(1);
		page.setBno(1236L);
		replyMapper.getListReply(page).stream().forEach(dto -> log.info(dto));
	}

	@Test
	void insertReply() {
		for(int i = 0; i < 291; i++) {
			Reply reply = Reply.builder()
					.user_id("user1")
					.content("댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트댓글테스트" + i)
					.bno(1153L).build();

			replyMapper.insertReply(reply);
			log.info(reply.getRno());
		}
	}

	@Test
	void getOneReply() {
		log.info(replyMapper.getOneReply(45L));
	}

	@Test
	void deleteReply() {

		replyMapper.deleteReply(44L);
	}
}
