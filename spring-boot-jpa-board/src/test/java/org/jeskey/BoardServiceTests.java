package org.jeskey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	public void insertOneTest() {

	      BoardDTO boardDTO = BoardDTO.builder()
	                .title("타이틀 테스트 ")
	                .content("내용 테스트 ")
	                .build();

	      List<String> fileNameList = new ArrayList<>();

	      for (int i = 0; i < 3; i++) {
	    	  fileNameList.add("20240101_"+UUID.randomUUID()+"_파일이름"+i+".jpg");
	  		}

	      boardDTO.setFileNames(fileNameList);

	      boardService.insert(boardDTO);
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

		String[] targets = {"t","c"};

		PageRequestDTO pageDTO = PageRequestDTO.builder()
				.page(1)
				.keyword("3")
				.targets(targets).build();

		log.info(boardService.getList(pageDTO));
	}

    @Test
    public void testUpdate(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(687L)
                .title("제목 수정.......")
                .content("내용 수정........")
                .build();

	    List<String> fileNameList = new ArrayList<>();

	    for (int i = 0; i < 2; i++) {
	    	  fileNameList.add("20240101_"+UUID.randomUUID()+"_파일이름"+i+".jpg");
	    }

	    boardDTO.setFileNames(fileNameList);

        boardService.update(boardDTO);
    }
}
