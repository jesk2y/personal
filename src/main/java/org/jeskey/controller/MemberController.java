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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String signupPOST(@Valid MemberDTO dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			model.addAttribute("error", bindingResult.getFieldError());
			return "/member/signup";
		}

		int i = memberService.insertMember(dto);

		if(i == 1) {
			redirectAttributes.addFlashAttribute("msg", "회원가입이 완료되었습니다");
		}else {
			redirectAttributes.addFlashAttribute("msg", "회원가입 중에 문제가 생겼습니다");
		}

		return "redirect:/login";
	}


	@PostMapping("/login")
	public String loginPOST(MemberDTO memberParam, HttpSession session) {

		String returnURL = "";

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

	@GetMapping("/changepw")
	public String changepwGET() {


		return "/member/changepw";
	}

	@PostMapping("/changepw")
	public String changePwPOST(MemberDTO memberDTO, HttpSession session, RedirectAttributes redirectAttributes) {

		memberDTO.setUser_id((String)session.getAttribute("id"));

		int i = memberService.changePassword(memberDTO);

		if(i != 1) {
			redirectAttributes.addFlashAttribute("msg", "비밀번호 변경 중 문제가 생겼습니다");
			return "redirect:/changepw";
		}

		redirectAttributes.addFlashAttribute("msg", "비밀번호가 변경되었습니다");
		return "redirect:/memberInfo";
	}

	@GetMapping("/memberInfo")
	public String memberInfoGET(Model model, HttpSession session) {

		String user_id = (String) session.getAttribute("id");

		model.addAttribute("memberDTO", memberService.getMember(user_id));

		return "/member/info";
	}

	@PostMapping("/removeUser")
	public String removeUserPOST(HttpSession session, @RequestParam("password") String password
			, RedirectAttributes redirectAttributes) {

		String user_id = (String) session.getAttribute("id");
		int i = 0;

		if(new PasswordEncoder().ismatch(password, memberService.getMember(user_id).getPassword())){
			i = memberService.delMember(user_id);
		}

		if(i != 1) {
			redirectAttributes.addFlashAttribute("msg", "회원탈퇴 과정 중 문제가 발생하였습니다");
			return "redirect:/memberInfo";
		}

		redirectAttributes.addFlashAttribute("msg", "성공적으로 회원탈퇴를 하였습니다");
		session.removeAttribute("id");
		return "redirect:/login";
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
