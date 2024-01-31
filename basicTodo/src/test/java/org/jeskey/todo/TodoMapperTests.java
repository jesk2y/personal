package org.jeskey.todo;

import java.util.List;

import org.jeskey.todo.domain.TodoVO;
import org.jeskey.todo.mapper.TodoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TodoMapperTests {
	
	@Autowired
	private TodoMapper todoMapper;
	
	
	@Test
	public void insert() {
		TodoVO todoVO = TodoVO.builder()
				.content("투두 테스트")
				.build();
		
		todoMapper.insert(todoVO);
	}
	
	@Test
	public void getList() {
		List<TodoVO> voList = todoMapper.getList();
		
		voList.stream().forEach(vo -> log.info(vo));
	}
}
