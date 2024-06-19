package org.jeskey;

import org.jeskey.domain.Board;
import org.jeskey.domain.Reply;
import org.jeskey.dto.PageRequestDTO;
import org.jeskey.repository.ReplyRepository;
import org.jeskey.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

	@Autowired
	public ReplyRepository replyRepository;

	@Autowired
	public ReplyService replyService;

	@Test
	public void insertReply() {

		Board board = Board.builder()
				.bno(714L).build();

		for(int i = 0; i<133; i++) {
			Reply reply = Reply.builder()
					.replyText("댓글테스트" + i)
					.board(board).build();

			replyRepository.save(reply);
		}
	}

	@Test
	public void GetList(){
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.bno(715L)
				.page(-1).build();

		log.info(replyService.getListReply(pageRequestDTO));
	}

	@Test
	public void getCount() {
		log.info(replyRepository.countByBoardBno(715L));
	}
}
