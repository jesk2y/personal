package org.jeskey;

import java.util.UUID;

import org.jeskey.domain.Board;
import org.jeskey.dto.PageDTO;
import org.jeskey.mapper.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MapperTests {

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void getList() {

		PageDTO page = new PageDTO();
		page.setPage(3);//3페이지로 설정

		page.setCount(boardMapper.totalCount(page));	//카운트 먼저 설정

		log.info("시작: "+page.getStart()
				+ ", 끝: "+page.getEnd()
				+ ", 이전 페이지: "+page.isPrev()
				+", 다음 페이지: "+page.isNext());

		for(Board b : boardMapper.getListBoard(page)) {
			log.info(b);
		}
	}

	@Test
	public void getListWithSearch() {

		PageDTO page = new PageDTO();

		page.setTarget("tc");
		page.setKeyword("9");
		page.setCount(boardMapper.totalCount(page));

		for(Board b : boardMapper.getListBoard(page)) {
			log.info(b);
		}
	}

	@Test
	public void getOne() {
		log.info(boardMapper.getOneBoard(561L));
	}

	@Test
	public void insert() {

		for(int i = 0; i< 555; i++) {
			Board board = Board.builder()
					.title("제목" + i)
					.content("내용" + i)
					.user_id("user1")
					.build();
			System.out.println(boardMapper.insertBoard(board));
		}
	}

	@Test
	public void insertOne() {

		Board board = Board.builder()
				.title("작성테스트")
				.content("내용" )
				.user_id("user1")
				.build();

		boardMapper.insertBoard(board);

		System.out.println(board.getBno());
	}

	@Test
	public void update() {

		Board board = Board.builder()
				.bno(25L)
				.title("수정테스트")
				.content("수정테스트").build();

		log.info(boardMapper.updateBoard(board));	//1 출력
		log.info(boardMapper.getOneBoard(25L));
	}

	@Test
	public void delete() {
		log.info(boardMapper.deleteBoard(24L));	//1 출력
	}

	@Test
	public void insertWithImage() {

		Board board = Board.builder()
				.title("이미지첨부테스트")
				.content("내용" )
				.user_id("user1")
				.build();

		long bno = boardMapper.insertBoard(board);

		for(int i = 0; i<3; i++) {
			board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
		}

		log.info("bno: "+board.getBno());
	}

	@Test
	public void getOneWithImage() {
		log.info(boardMapper.getOneBoard(1119L));
	}

}
