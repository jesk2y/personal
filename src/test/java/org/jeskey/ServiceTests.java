package org.jeskey;

import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageDTO;
import org.jeskey.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ServiceTests {

	@Autowired
	private BoardService boardService;

	@Test
	public void getList() {

		PageDTO page = new PageDTO();

		page.setTarget("tc");
		page.setKeyword("9");

		for(BoardDTO b : boardService.getList(page)) {
			log.info(b);
		}
	}

	@Test
	public void insert() {

		BoardDTO dto = BoardDTO.builder()
				.title("작성테스트")
				.content("작성테스트")
				.user_id("user1")
				.build();

		boardService.insert(dto);

	}



	@Test
	public void update() {

		BoardDTO dto = BoardDTO.builder()
				.bno(25L)
				.title("수정테스트2")
				.content("수정테스트2").build();

		log.info(boardService.update(dto));
		log.info(dto.getBno());
	}

	@Test
	public void delete() {
		log.info(boardService.delete(23L));
	}
}
