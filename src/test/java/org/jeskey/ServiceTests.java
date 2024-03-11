package org.jeskey;

import java.util.Arrays;
import java.util.UUID;

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
	public void getOne() {

		PageDTO page = new PageDTO();

		page.setTarget("tc");
		page.setKeyword("9");

		log.info(boardService.getOne(651L));
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
		log.info(boardService.delete(560L));
	}

	@Test
	public void insertWithImage() {
		BoardDTO boardDTO = BoardDTO.builder()
				.title("파일첨부 테스트")
				.content("내용 테스트")
				.user_id("user1")
				.build();

		boardDTO.setFileNames(
				Arrays.asList(
					UUID.randomUUID()+"_aaa.jpg",
					UUID.randomUUID()+"_bbb.jpg",
					UUID.randomUUID()+"_ccc.jpg"
				));

		Long bno = boardService.insert(boardDTO);

		log.info("bno: "+bno);
	}

	@Test
	public void updateWithImage() {

		BoardDTO dto = BoardDTO.builder()
				.bno(561L)
				.title("이미지 수정테스트")
				.content("수정테스트").build();

		dto.setFileNames(
				Arrays.asList(
					UUID.randomUUID()+"_aaa_수정.jpg",
					UUID.randomUUID()+"_bbb_수정.jpg"
				));

		boardService.update(dto);
	}
}
