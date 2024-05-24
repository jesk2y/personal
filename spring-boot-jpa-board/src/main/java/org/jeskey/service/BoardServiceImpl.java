package org.jeskey.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeskey.domain.Board;
import org.jeskey.dto.BoardDTO;
import org.jeskey.repository.BoardRepository;
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
    public List<BoardDTO> getList() {

        List<BoardDTO> list = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "bno"))
                .stream().map(board -> entityToDto(board))
                .collect(Collectors.toList());

        return list;
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
