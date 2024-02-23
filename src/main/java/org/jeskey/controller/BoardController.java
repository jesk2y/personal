package org.jeskey.controller;

import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageDTO;
import org.jeskey.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/list")
	public void listGET(@ModelAttribute("pageObj") PageDTO dto, Model model) {

		model.addAttribute("listDTO",boardService.getList(dto));
	}

	@GetMapping("/content")
	public String contentGET(@ModelAttribute("pageObj") PageDTO dto, Model model) {

		model.addAttribute("contentDTO", boardService.getOne(dto.getBno()));
		model.addAttribute("listDTO",boardService.getList(dto));

		return "/board/content";
	}

	@GetMapping("/register")
	public String registerGET(Model model) {
		model.addAttribute("boardDTO", new BoardDTO());
		return "/board/writePage";
	}

	@PostMapping("/register")
	public String registerPOST(@Valid BoardDTO dto, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {

			return "/board/writePage";
		}

		Long bno = boardService.insert(dto);

		return "redirect:/board/content?bno="+ bno;
	}

	@GetMapping("/update")
	public String updateGET(@ModelAttribute("pageObj") PageDTO dto, Model model) {

		model.addAttribute("boardDTO", boardService.getOne(dto.getBno()));

		return "/board/writePage";
	}

	@PostMapping("/update")
	public String updatePOST(@ModelAttribute("pageObj") PageDTO pageDTO,
			@Valid BoardDTO boardDTO, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {

			return "/board/writePage";
		}

		Long bno = boardService.insert(boardDTO);

		return "redirect:/board/content"+pageDTO.getLink(bno);
	}

	@PostMapping("/delete")
	public String deletePOST(@RequestParam("bno")Long bno) {
		//post는 requestParam 안붙이면 에러남?

		System.out.println(bno);

		boardService.delete(bno);

		return "redirect:/board/list";
	}
}
