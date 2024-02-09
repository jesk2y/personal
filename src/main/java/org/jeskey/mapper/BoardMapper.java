package org.jeskey.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.jeskey.domain.Board;

@Mapper
public interface BoardMapper {

	void insertBoard(Board board);
}
