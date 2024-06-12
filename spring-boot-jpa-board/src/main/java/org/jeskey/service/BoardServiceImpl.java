package org.jeskey.service;

import java.util.List;
import java.util.Optional;

import org.jeskey.domain.Board;
import org.jeskey.domain.BoardAttach;
import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageRequestDTO;
import org.jeskey.dto.PageResponseDTO;
import org.jeskey.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;

	public BoardDTO entityToDto(Board board){
	   BoardDTO boardDTO = BoardDTO.builder()
	            .bno(board.getBno())
	            .title(board.getTitle())
	            .content(board.getContent())
	            .regdate(board.getRegdate())
	            .updatedate(board.getUpdatedate())
	            .build();

	   if(board.getFileList() != null) {
		   boardDTO.setFileList(board.getFileList());
	   }

	    return boardDTO;
	}

	public Board dtoToEntity(BoardDTO boardDTO){
	   Board board = Board.builder()
	            .title(boardDTO.getTitle())
	            .content(boardDTO.getContent())
	            .build();

	   if(boardDTO.getFileList() != null){
		   boardDTO.getFileList().forEach(file
				   -> board.addFile(file.getDate(), file.getUuid(), file.getFileName()));
	   }

	    return board;
	}

	@Override
	public PageResponseDTO<BoardDTO> getList(PageRequestDTO dto) {

		Page<Board> result = boardRepository.getListWithPageAndSearch(dto);

		List<BoardDTO> list = result.getContent().stream().map(vo -> entityToDto(vo)).toList();

		return PageResponseDTO.<BoardDTO>builder()
	            .pageRequestDTO(dto)
	            .dtoList(list)
	            .total((int) result.getTotalElements())
	            .build();
	}

	@Override
	public Long insert(BoardDTO boardDTO) {

	    Board board = dtoToEntity(boardDTO);
	    Long bno = boardRepository.save(board).getBno();

	    return bno;
	}

	@Override
	public BoardDTO getOne(Long bno) {

	    Optional<Board> result = boardRepository.findByIdWithImages(bno);
	    Board board = result.orElseThrow();

	    BoardDTO boardDTO = entityToDto(board);

	    return boardDTO;
	}

	@Override
	public void delete(Long bno) {

		boardRepository.deleteImagesById(bno);
	}

	@Override
	@Transactional
	public Long update(BoardDTO dto) {

		//기존 파일 삭제
		Optional<Board> result = boardRepository.findById(dto.getBno());
		Board board = result.orElseThrow();
	    board.clearFiles();

	    //새 파일 등록
	    List<BoardAttach> fileList = dtoToEntity(dto).getFileList();

	    fileList.forEach(file
	    		-> board.addFile(file.getDate(), file.getUuid(), file.getFile_name()));

	    //글 수정
		board.update(dto.getTitle(), dto.getContent());

		return dto.getBno();
	}
}
