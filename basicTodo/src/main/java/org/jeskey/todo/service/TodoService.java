package org.jeskey.todo.service;

import java.util.List;

import org.jeskey.todo.domain.TodoDTO;
import org.jeskey.todo.domain.TodoVO;


public interface TodoService {
	void register(TodoDTO todoDTO);
	
	List<TodoDTO> getList();
	
	void finish(TodoDTO todoDTO);
	
	void remove(Long tno);
}
