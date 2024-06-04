package org.jeskey.controller;

import org.jeskey.dto.BoardDTO;
import org.jeskey.dto.PageRequestDTO;
import org.jeskey.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/list")
	public void listGET(@ModelAttribute("pageObj") @Valid PageRequestDTO dto, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			dto = new PageRequestDTO();
		}

		model.addAttribute("pagination",boardService.getList(dto));
	}

	@GetMapping("/content")
	public String contentGET(@ModelAttribute("pageObj") @Valid PageRequestDTO dto, BindingResult bindingResult,
			@RequestParam("bno") Long bno, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		if(bindingResult.hasErrors()) {
			dto = new PageRequestDTO();
		}

		model.addAttribute("contentDTO", boardService.getOne(bno));
		model.addAttribute("pagination",boardService.getList(dto));

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

		long bno = boardService.insert(dto);

		return "redirect:/board/content?bno="+ bno;
	}

	@GetMapping("/update")
	public String updateGET(@ModelAttribute("pageObj") @Valid PageRequestDTO dto, BindingResult bindingResult,
			@RequestParam("bno") Long bno, Model model) {

		if(bindingResult.hasErrors()) {
			dto = new PageRequestDTO();
		}

		model.addAttribute("boardDTO", boardService.getOne(bno));

		return "/board/writePage";
	}

	@PostMapping("/update")
	public String updatePOST(@ModelAttribute("pageObj") PageRequestDTO pageDTO,
			@Valid BoardDTO dto, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {

			return "/board/writePage";
		}

		Long bno = boardService.update(dto);

		return "redirect:/board/content"+pageDTO.getLink(bno);
	}

	@PostMapping("/delete")
	public String deletePOST(@RequestParam("bno")Long bno, Model model, HttpSession session) {

		boardService.delete(bno);
		return "redirect:/board/list";
	}
}
