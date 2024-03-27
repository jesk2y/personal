package org.jeskey.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeskey.dto.PageDTO;
import org.jeskey.dto.ReplyDTO;
import org.jeskey.service.ReplyService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;

	@Operation(summary = "replyList GET", description = "Get 방식으로 댓글 리스트 가져오기")
	@GetMapping(value="/list/{bno}")
	public List<ReplyDTO> getReplyList(@PathVariable("bno") Long bno, PageDTO pageDTO) {

		return replyService.getListReply(pageDTO);
	}

	@Operation(summary = "Reply GET", description = "Get 방식으로 댓글 가져오기")
	@GetMapping(value="/{rno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ReplyDTO getReply(@PathVariable("rno") Long rno) {

		return replyService.getOneReply(rno);
	}

	@Operation(summary = "register POST", description = "Post 방식으로 댓글 입력하기")
	@PostMapping(value="/", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			//나중에 처리
		}

		System.out.println(replyDTO);

		Map<String, Long> resultMap = new HashMap<>();

		Long rno = replyService.insertReply(replyDTO);

		resultMap.put("rno", rno);

		return resultMap;
	}

	@Operation(summary = "update PUT", description = "Put 방식으로 댓글 수정하기")
	@PutMapping(value="/", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Long> update(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			//나중에 처리
		}

		Map<String, Long> resultMap = new HashMap<>();

		Long rno = replyService.updateReply(replyDTO);

		resultMap.put("rno", rno);

		return resultMap;
	}

	@Operation(summary = "delete", description = "Delete 방식으로 댓글 삭제하기")
	@DeleteMapping(value="/{rno}")
	public Map<String, Long> delete(@PathVariable("rno") Long rno) {

		Map<String, Long> resultMap = new HashMap<>();

		replyService.deleteReply(rno);

		resultMap.put("rno", rno);

		return resultMap;
	}
}
