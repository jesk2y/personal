package org.jeskey.controller;

import org.jeskey.dto.PageDTO;
import org.jeskey.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageObj") PageDTO dto, Model model) {
		
		dto.setLink(null);
		
		model.addAttribute("responseDTO",boardService.getList(dto));
	}
	
	@GetMapping("/register")
	public String registerGET() {
		return "/board/register";
	}
}
