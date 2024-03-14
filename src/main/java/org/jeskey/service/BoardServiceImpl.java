package org.jeskey.service;

import java.util.List;

import org.jeskey.domain.Board;
import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageDTO;
import org.jeskey.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	private BoardDTO EntityToDto(Board vo) {

		BoardDTO dto = BoardDTO.builder()
				.bno(vo.getBno())
				.title(vo.getTitle())
				.content(vo.getContent())
				.user_id(vo.getUser_id())
				.regdate(vo.getRegdate())
				.count_visit(vo.getCount_visit()).build();

		if (vo.getFileList() != null) {
			List<String> fileNameList = vo.getFileList().stream().map(
					file -> file.getDate() + "_" + file.getUuid() + "_" + file.getFile_name()
					).toList();

			dto.setFileNames(fileNameList);
		}

		return dto;
	}

	private Board DtoToEntity(BoardDTO dto) {

		Board vo = Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent())
				.user_id(dto.getUser_id()).build();

		if (dto.getBno() != null) {
			dto.setBno(vo.getBno());
		}

		return vo;
	}

	@Override
	public Long insert(BoardDTO dto) {

		Board board = DtoToEntity(dto);
		boardMapper.insertBoard(board);

		return board.getBno();
	}

	@Override
	public BoardDTO getOne(Long bno) {

		Board board = boardMapper.getOneBoard(bno);

		boardMapper.addCount(bno);// 조회수 증가

		return EntityToDto(board);
	}

	@Override
	@Transactional
	public Long update(BoardDTO dto) {

		// boardMapper.clearImage(dto.getBno());

		Board board = DtoToEntity(dto);

		boardMapper.updateBoard(board);

		/*
		 * if(board.getImageSet() != null) { for(File image: board.getImageSet()) {
		 * image.setBno(board.getBno()); //boardMapper.insertImage(image); } }
		 */
		return dto.getBno();
	}

	@Override
	public int delete(Long bno) {

		return boardMapper.deleteBoard(bno);
	}

	@Override
	public List<BoardDTO> getList(PageDTO dto) {

		dto.setCount(boardMapper.totalCount(dto));
		List<BoardDTO> dtoList = boardMapper.getListBoard(dto).stream().map(vo -> EntityToDto(vo)).toList();
		return dtoList;
	}
}
