package org.jeskey.todo;

import org.jeskey.todo.domain.TodoDTO;
import org.jeskey.todo.domain.TodoVO;
import org.jeskey.todo.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TodoServiceTests {

	@Autowired
	private TodoService todoService;
	
	@Test
	public void insert() {
		
		TodoDTO dto = TodoDTO.builder()
				.content("서비스 테스트").build();
		
		todoService.register(dto);
	}
	
	@Test
	public void getList() {
		
		todoService.getList().stream().forEach(dto -> 
				log.info(dto));
		
	}
	
	@Test
	public void finish() {
		
		TodoDTO dto = TodoDTO.builder()
		.tno(83L)
		.finished(false)
		.build();
		
		todoService.finish(dto);
	}
	
	@Test
	public void remove() {
		
		todoService.remove(65L);
	}
	
}
