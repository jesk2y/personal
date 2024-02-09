package org.jeskey;

import org.jeskey.domain.Board;
import org.jeskey.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void insert() {
		
		for(int i = 0; i< 25; i++) {
			Board board = Board.builder()
					.title("제목" + i)
					.content("내용" + i)
					.password("1111")
					.build();	
			log.info(i);
			boardMapper.insertBoard(board);
		}
	}
}
