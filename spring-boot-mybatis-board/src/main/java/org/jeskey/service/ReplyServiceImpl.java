package org.jeskey.service;

import java.util.List;

import org.jeskey.domain.Reply;
import org.jeskey.dto.PageDTO;
import org.jeskey.dto.ReplyDTO;
import org.jeskey.mapper.ReplyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

	private final ReplyMapper replyMapper;

	private final ModelMapper modelMapper;

	@Override
	public List<ReplyDTO> getListReply(PageDTO dto) {

		dto.setReply(replyMapper.totalCount(dto.getBno()));	//댓글용 display, length, count 설정

		int page = (dto.getPage() == -1) ? dto.getLastPage() : dto.getPage();
		dto.setPage(page);
		//페이지가 -1이면 처음 페이지이므로 마지막 페이지로 이동

		dto.setPaging(dto.getCount());	//페이지네이션 설정


		List<ReplyDTO> replyList = replyMapper.getListReply(dto).stream()
				.map(vo -> modelMapper.map(vo, ReplyDTO.class)).toList();

		return replyList;
	}

	@Override
	public Long insertReply(ReplyDTO dto) {

		if(dto.getPno() != null && replyMapper.getOneReply(dto.getPno()) == null) {
			throw new DataIntegrityViolationException("댓글 삭제됨");
		};


		Reply reply = modelMapper.map(dto, Reply.class);
		replyMapper.insertReply(reply);

		Long rno = reply.getRno();

		return rno;
	}

	@Override
	public void deleteReply(Long rno) {

		replyMapper.deleteReply(rno);
	}

	@Override
	public ReplyDTO getOneReply(Long rno) {

		ReplyDTO replyDTO = modelMapper.map(replyMapper.getOneReply(rno), ReplyDTO.class);

		return replyDTO;
	}
}
