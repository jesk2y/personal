package org.jeskey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeskey.domain.Board;
import org.jeskey.dto.PageDTO;

@Mapper
public interface BoardMapper {
	
	List<Board> getListBoard(PageDTO page);	//페이지 파라미터 추가
	
	Board getOneBoard(Long bno);

	int insertBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(Long bno);
	
	int totalCount(PageDTO page);
		
}
