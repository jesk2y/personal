package org.jeskey.service;

import java.util.List;

import org.jeskey.dto.BoardDTO;

public interface BoardService {

	public List<BoardDTO> getList();

	public Long insert(BoardDTO dto);

	public BoardDTO getOne(Long bno);

	public Long update(BoardDTO dto);

	public void delete(Long bno);
}
