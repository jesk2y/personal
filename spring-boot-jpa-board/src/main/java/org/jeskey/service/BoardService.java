package org.jeskey.service;

import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageRequestDTO;
import org.jeskey.dto.PageResponseDTO;

public interface BoardService {

	public PageResponseDTO<BoardDTO> getList(PageRequestDTO dto);

	public Long insert(BoardDTO dto);

	public BoardDTO getOne(Long bno);

	public Long update(BoardDTO dto);

	public void delete(Long bno);
}
