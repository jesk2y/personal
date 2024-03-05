package org.jeskey.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jeskey.domain.Board;
import org.jeskey.domain.BoardImage;
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

		if(vo.getImageSet() != null){
			List<String> fileNames = vo.getImageSet().stream().sorted().map(
					boardImage -> boardImage.getUuid()+"_"+boardImage.getFileName())
						.collect(Collectors.toList());
			dto.setFileNames(fileNames);
		}

		return dto;
	}

	private Board DtoToEntity(BoardDTO dto) {

		Set<BoardImage> set = new HashSet<>();

		Board vo = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.user_id(dto.getUser_id())
				.build();

		if(dto.getFileNames() != null){
			dto.getFileNames().forEach(fileName -> {
				String[] arr = fileName.split("_");
				vo.addImage(arr[0], arr[1]);
			});
		}

		return vo;
	}

	@Override
	public Long insert(BoardDTO dto) {

		Board board = DtoToEntity(dto);

		int i = boardMapper.insertBoard(board);

		if(board.getImageSet() != null) {
			for(BoardImage image: board.getImageSet()) {
				boardMapper.insertImage(image);
			}
		}
		return board.getBno();
	}

	@Override
	public BoardDTO getOne(Long bno) {

		Board board = boardMapper.getOneBoard(bno);

		boardMapper.addCount(bno);//조회수 증가

		return EntityToDto(board);
	}

	@Override
	public Long update(BoardDTO dto) {

		boardMapper.clearImage(dto.getBno());

		Board board = DtoToEntity(dto);

		int i = boardMapper.updateBoard(board);

		return dto.getBno();
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
