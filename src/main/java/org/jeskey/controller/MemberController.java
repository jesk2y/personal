package org.jeskey.controller;

import java.util.HashMap;
import java.util.Map;

import org.jeskey.common.PasswordEncoder;
import org.jeskey.dto.MemberDTO;
import org.jeskey.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/signup")
	public String signupGET(Model model) {

		model.addAttribute("memberDTO", new MemberDTO());

		return "/member/signup";
	}

	@PostMapping("/signup")
	public String signupPOST(@Valid MemberDTO dto, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			model.addAttribute("error", bindingResult.getFieldError());
			return "/member/signup";
		}

		memberService.insertMember(dto);

		return "redirect:/login";
	}


	@PostMapping("/login")
	public String loginPOST(MemberDTO memberParam, HttpSession session) {

		String returnURL = "";

		if(session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}

		session.setAttribute("id", memberParam.getUser_id());

		String priorURL = (String) session.getAttribute("prev_url");

		if(priorURL != null) {
			returnURL = priorURL;

		}else {
			returnURL = "/board/list";
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

	@GetMapping("/changePw")
	public void changePwGET() {

	}

	@PostMapping("/changePw")
	public String changePwPOST(MemberDTO memberDTO) {

		memberService.changePassword(memberDTO);

		return "redirect:/login";
	}

	@GetMapping("/memberInfo")
	public String memberInfoGET(Model model) {

		String user_id = "user_1";//임시

		model.addAttribute("MemberDTO", memberService.getMember(user_id));

		return "/member/info";
	}

	//=====REST=====

	//로그인 체크
	@Operation(summary = "check Login POST", description = "Post 방식으로 로그인 체크하기")
	@PostMapping(value="/checkLogin", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Map<String, String> checkLogin(@RequestBody MemberDTO memberParam){

		Map<String, String> resultMap = new HashMap<>();

		MemberDTO member = memberService.getMember(memberParam.getUser_id());

		System.out.println(memberParam.getPassword());

		if(member == null || !new PasswordEncoder().ismatch(memberParam.getPassword(), member.getPassword())) {
			//아이디가 없거나 비밀번호가 일치하지 않으면
			resultMap.put("checked", "false");

		}else {
			resultMap.put("checked", "true");
		}

		return resultMap;
	}


	//아이디 중복체크
	@Operation(summary = "check ID GET", description = "GET 방식으로 아이디 중복 체크하기")
	@GetMapping(value="/checkId/{user_id}")
	public @ResponseBody Map<String, String> checkID(@PathVariable("user_id") String user_id){

		Map<String, String> resultMap = new HashMap<>();
		if(memberService.getMember(user_id) != null) {
			resultMap.put("checked", "existed");

		}else {
			resultMap.put("checked", "new");
		}

		return resultMap;
	}
}
