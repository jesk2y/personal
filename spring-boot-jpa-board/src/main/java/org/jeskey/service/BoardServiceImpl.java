package org.jeskey.service;

import java.util.List;
import java.util.Optional;

import org.jeskey.domain.Board;
import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageRequestDTO;
import org.jeskey.dto.PageResponseDTO;
import org.jeskey.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                .regdate(board.getRegDate())
                .updatedate(board.getUpdateDate())
                .build();

        return boardDTO;
    }

    public Board dtoToEntity(BoardDTO boardDTO){
       Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build();

        return board;
    }

	@Override
	public PageResponseDTO<BoardDTO> getList(PageRequestDTO dto) {

		Pageable pageable = PageRequest.of(dto.getPage()-1, dto.getDisplay(), Sort.by("bno").descending());
		Page<Board> result = boardRepository.findAll(pageable);

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

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        BoardDTO boardDTO = entityToDto(board);

        return boardDTO;
    }

    @Override
    public void delete(Long bno) {

    	boardRepository.deleteById(bno);
    }

	@Override
	@Transactional
	public Long update(BoardDTO dto) {

		Optional<Board> result = boardRepository.findById(dto.getBno());
	    Board board = result.orElseThrow();

		board.update(dto);

		return dto.getBno();
	}
}
