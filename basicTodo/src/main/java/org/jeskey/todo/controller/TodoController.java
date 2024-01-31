package org.jeskey.todo.controller;

import org.jeskey.todo.domain.TodoDTO;
import org.jeskey.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	@GetMapping("")
	public void listGET(Model model) {

		model.addAttribute("list", todoService.getList()); 
		
	}
	
	@PostMapping("/register")
	public String registerPOST(TodoDTO todoDTO) {
		
		todoService.register(todoDTO);
		
		return "redirect:/todo";
	}
	
	@PostMapping("/remove")
	public String removePOST(Long tno) {

		todoService.remove(tno);
	
		return "redirect:/todo";
	}
	
	@PostMapping("/check")
	public String checkPOST(TodoDTO todoDTO) {
		
		todoService.finish(todoDTO);
		
		return "redirect:/todo";
	}
}
