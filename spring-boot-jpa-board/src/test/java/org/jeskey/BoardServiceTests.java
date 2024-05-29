package org.jeskey;

import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageRequestDTO;
import org.jeskey.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

	@Autowired
	public BoardService boardService;

	@Test
	public void insertTest() {

		 for(int i = 0; i< 223; i++){
	          BoardDTO boardDTO = BoardDTO.builder()
	                    .title("타이틀 테스트 "+i)
	                    .content("내용 테스트 "+i)
	                    .build();

	          boardService.insert(boardDTO);
	     }
	}

	@Test
    public void getOne(){

        BoardDTO boardDTO = boardService.getOne(1L);
        log.info(boardDTO);;
    }

    @Test
    public void testDelete(){
        boardService.delete(2L);
    }

		@Test
		public void testGetList() {

			PageRequestDTO pageDTO = PageRequestDTO.builder()
					.page(2).build();

			log.info(boardService.getList(pageDTO));

		}

    @Test
    public void testUpdate(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(223L)
                .title("제목 수정.......")
                .content("내용 수정........")
                .build();

        boardService.update(boardDTO);
    }
}
