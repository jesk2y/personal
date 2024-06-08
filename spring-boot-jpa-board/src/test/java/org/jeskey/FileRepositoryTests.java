package org.jeskey;

import java.util.Optional;
import java.util.UUID;

import org.jeskey.domain.Board;
import org.jeskey.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FileRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void testInsertWithImages() {
		Board board = Board.builder().title("파일첨부테스트").content("테스트").build();

		for (int i = 0; i < 3; i++) {
			board.addFile(UUID.randomUUID().toString(), "file" + i + ".jpg");
		}

		boardRepository.save(board);
	}

	@Test
	public void testReadWithImages() {

		Optional<Board> result = boardRepository.findByIdWithImages(680L);

		Board board = result.orElseThrow();

		log.info(board);
		log.info("==========");
		log.info(board.getFileList());
	}

	@Test
	public void testDeleteWithImages() {

		boardRepository.deleteById(679L);
	}

	@Test
	@Transactional
	@Commit
	public void testUpdateWithImage() {

		Optional<Board> result = boardRepository.findById(680L);
		Board board = result.orElseThrow();

		board.clearFiles();

		for(int i = 0; i<2; i++) {
				board.addFile(UUID.randomUUID().toString(),
		  				"updatefile"+i+".jpg");
		  }

		boardRepository.save(board);
	}
}
