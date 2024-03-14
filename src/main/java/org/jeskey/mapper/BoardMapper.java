package org.jeskey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeskey.domain.Board;
import org.jeskey.domain.File;
import org.jeskey.dto.PageDTO;

@Mapper
public interface BoardMapper {

	List<Board> getListBoard(PageDTO page);	//페이지 파라미터 추가

	Board getOneBoard(Long bno);

	int addCount(Long bno);

	int insertBoard(Board board);

	int updateBoard(Board board);

	int deleteBoard(Long bno);

	int totalCount(PageDTO page);

	//파일
	int saveFiles(List<File> files);

	int clearFiles(Long bno);
}
