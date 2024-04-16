package org.jeskey.controller;

import org.jeskey.common.PasswordEncoder;
import org.jeskey.dto.MemberDTO;
import org.jeskey.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/login")
	public String loginPOST(MemberDTO memberDTO, HttpSession session,
					HttpServletResponse response, RedirectAttributes redirectAttributes) {

		String returnURL = "";

		if(session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}

		MemberDTO joinedMember = memberService.getMember(memberDTO.getUser_id());

		if(new PasswordEncoder().ismatch(memberDTO.getPassword(), joinedMember.getPassword())) {	//패스워드 일치

				session.setAttribute("id", memberDTO.getUser_id());

				String priorURL = (String) session.getAttribute("prev_url");

				if(priorURL != null) {
					returnURL = priorURL;

				}else {
					returnURL = "/board/list";
				}
		}else {
			redirectAttributes.addFlashAttribute("wrongIdOrPassword");
			returnURL = "/login";
		}

		return "redirect:" + returnURL;
	}

	@GetMapping("/login")
	public String loginGET() {


		return "/member/login";
	}

	@GetMapping("/logout")
	public String logoutGET(HttpSession session, HttpServletResponse response) {

		session.removeAttribute("id");


		String priorURL = (String) session.getAttribute("prev_url");

		if(priorURL != null) {
			return "redirect:" + priorURL;

		}else {
			return "redirect:/board/list";
		}

	}
}
