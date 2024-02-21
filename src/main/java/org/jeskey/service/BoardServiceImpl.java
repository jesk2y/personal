package org.jeskey.service;

import java.util.List;

import org.jeskey.domain.Board;
import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageDTO;
import org.jeskey.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper boardMapper;
	
	private BoardDTO EntityToDto(Board vo) {
		
		BoardDTO dto = BoardDTO.builder()
				.bno(vo.getBno())
				.title(vo.getTitle())
				.content(vo.getContent())
				.user_id(vo.getUser_id())
				.regdate(vo.getRegdate())
				.count_visit(vo.getCount_visit())
				.build();
		
		return dto;
	}
	
	private Board DtoToEntity(BoardDTO dto) {
		
		Board vo = Board.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.user_id(dto.getUser_id())
				.build();
		
		return vo;
	}

	
	@Override
	public Long insert(BoardDTO dto) {
		
		Board board = DtoToEntity(dto);
		
		int i = boardMapper.insertBoard(board);
		
		return board.getBno();
	}

	@Override
	public BoardDTO getOne(Long bno) {
		
		Board board = boardMapper.getOneBoard(bno);
		
		return EntityToDto(board);
	}

	@Override
	public Long update(BoardDTO dto) {
		
		Board board = DtoToEntity(dto);
		
		int i = boardMapper.updateBoard(board);
		
		return board.getBno();
	}

	@Override
	public int delete(Long bno) {
		
		return boardMapper.deleteBoard(bno);
	}

	@Override
	public List<BoardDTO> getList(PageDTO dto) {

		dto.setCount(boardMapper.totalCount(dto));
		List<BoardDTO> dtoList = 
				boardMapper.getListBoard(dto).stream().map(vo -> EntityToDto(vo)).toList();
		return dtoList;
	}
}
