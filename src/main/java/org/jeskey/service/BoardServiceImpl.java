package org.jeskey.service;

import java.util.List;
import java.util.Optional;

import org.jeskey.domain.Board;
import org.jeskey.dto.BoardAttachDTO;
import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageDTO;
import org.jeskey.mapper.BoardMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final FileService fileService;

	private final ModelMapper modelMapper;

	private BoardDTO EntityToDto(Board vo) {

		BoardDTO dto = BoardDTO.builder()
				.bno(vo.getBno())
				.title(vo.getTitle())
				.content(vo.getContent())
				.user_id(vo.getUser_id())
				.regdate(vo.getRegdate())
				.count_visit(vo.getCount_visit()).build();

		if (vo.getFileList() != null) {
			List<BoardAttachDTO> fileList = vo.getFileList().stream().map(
					file -> BoardAttachDTO.builder()
							.date(file.getDate())
							.uuid(file.getUuid())
							.fileName(file.getFile_name()).build()
					).toList();

			dto.setFileList(fileList);
		}

		return dto;
	}

	private Board DtoToEntity(BoardDTO dto) {

		Board vo = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.user_id(dto.getUser_id()).build();

		return vo;
	}

	@Override
	public Long insert(BoardDTO dto) {

		Board board = DtoToEntity(dto);
		boardMapper.insertBoard(board);

		Long bno = board.getBno();
		fileService.saveFiles(bno, dto.getFileNames());	//파일정보 DB 업로드

		return bno;
	}

	@Override
	public BoardDTO getOne(Long bno) {

		//null이면 예외 발생
		Board board = Optional.ofNullable(boardMapper.getOneBoard(bno))
					.orElseThrow(() -> new NullPointerException("존재하지 않는 글입니다"));

		return EntityToDto(board);
	}

	@Override
	@Transactional
	public Long update(BoardDTO dto) {

		Board board = DtoToEntity(dto);

		boardMapper.updateBoard(board);	//게시물 수정
		boardMapper.clearFiles(dto.getBno());	//DB 파일정보 전부 삭제
		fileService.saveFiles(dto.getBno(), dto.getFileNames());	//파일정보 DB 업로드

		return dto.getBno();
	}

	@Override
	public int delete(Long bno) {

		return boardMapper.deleteBoard(bno);
	}

	@Override
	public List<BoardDTO> getList(PageDTO dto) {

		dto.setPaging(boardMapper.totalCount(dto));
		List<BoardDTO> dtoList = boardMapper.getListBoard(dto).stream().map(vo -> EntityToDto(vo)).toList();
		return dtoList;
	}
}
