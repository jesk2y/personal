package org.jeskey.todo.mapper;

import java.util.List;

import org.jeskey.todo.domain.TodoVO;

public interface TodoMapper {
	
	void insert(TodoVO todoVO);
	
	List<TodoVO> getList();
	
	void finish(TodoVO todoVO);
	
	void delete(Long tno);
}
