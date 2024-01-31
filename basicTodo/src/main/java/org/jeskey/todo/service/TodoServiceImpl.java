package org.jeskey.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jeskey.todo.domain.TodoDTO;
import org.jeskey.todo.domain.TodoVO;
import org.jeskey.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class TodoServiceImpl implements TodoService{
	
	private final TodoMapper todoMapper;
	
	@Override
	public void register(TodoDTO todoDTO) {

		TodoVO todoVO = TodoVO.builder()
		.content(todoDTO.getContent()).build();
		
		todoMapper.insert(todoVO);
	}

	@Override
	public List<TodoDTO> getList() {
		
		List<TodoDTO> dtoList = todoMapper.getList().stream().map(vo -> 
		TodoDTO.builder()
		.tno(vo.getTno())
		.content(vo.getContent())
		.finished(vo.getFinished()=='0'?false:true).build())
				.collect(Collectors.toList());
		
		return dtoList;
	}	
	
	@Override
	public void finish(TodoDTO todoDTO) {
		TodoVO vo = null;
		
		if(!todoDTO.isFinished()) {
			vo = TodoVO.builder()
			.tno(todoDTO.getTno())
			.finished('1').build();
		}else{
			vo = TodoVO.builder()
			.tno(todoDTO.getTno())
			.finished('0').build();
		}
		
		todoMapper.finish(vo);
	}

	@Override
	public void remove(Long tno) {
		todoMapper.delete(tno);
		
	}
}
