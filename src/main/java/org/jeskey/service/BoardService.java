package org.jeskey.service;

import java.util.List;

import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageDTO;

public interface BoardService {
	
	public List<BoardDTO> getList(PageDTO dto);
	
	public Long insert(BoardDTO dto);
	
	public BoardDTO getOne(Long bno);
	
	public Long update(BoardDTO dto);
	
	public int delete(Long bno);
	
}
