package org.jeskey;

import org.jeskey.domain.Reply;
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
	void insertReply() {
		for(int i = 0; i < 45; i++) {
			Reply reply = Reply.builder()
					.user_id("user1")
					.content("댓글테스트")
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
	void updateReply() {
		Reply reply = Reply.builder()
				.rno(45L)
				.content("댓글 수정").build();


		replyMapper.updateReply(reply);
		log.info(replyMapper.getOneReply(45L));
	}

	@Test
	void deleteReply() {

		replyMapper.deleteReply(44L);
	}
}
