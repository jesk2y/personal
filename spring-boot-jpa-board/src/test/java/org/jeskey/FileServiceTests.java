package org.jeskey;


import java.util.UUID;

import org.jeskey.domain.Board;
import org.jeskey.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FileServiceTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void imageInsert() {
		Board board = Board.builder()
				.title("파일첨부테스트")
				.content("테스트")
				.build();

		for(int i = 0; i<3; i++) {
			board.addFile(UUID.randomUUID().toString(), "file"+i+".jpg");
		}

		boardRepository.save(board);
	}

	@Test
	public void imageDeleteAll() {

		boardRepository.deleteById(678L);
	}
}
