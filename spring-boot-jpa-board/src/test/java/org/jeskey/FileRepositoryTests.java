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
			board.addFile("240611",UUID.randomUUID().toString(), "file" + i + ".jpg");
		}

	    System.out.println(board);
		boardRepository.save(board);
	}

	@Test
	public void testReadWithImages() {

		Optional<Board> result = boardRepository.findByIdWithImages(680L);

		Board board = result.orElseThrow();

		log.info(board);
		log.info(board.getFileList());
	}

	@Test
	@Transactional
	@Commit
	public void testDeleteWithImages() {

		boardRepository.deleteImagesById(683L);
		boardRepository.deleteById(683L);
	}

	@Test
	@Transactional
	@Commit
	public void testUpdateWithImage() {

		Board board = Board.builder()
				.bno(682L)//기존에 존재하는 bno
				.title("제목 수정.....")
				.content("내용 수정......")
				.build();

		for(int i = 0; i<2; i++) {
			board.addFile("240611",UUID.randomUUID().toString(),
		  				"updatefile"+i+".jpg");
		}

		boardRepository.save(board);
	}
}
