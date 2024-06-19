package org.jeskey.service;

import java.util.List;

import org.jeskey.domain.Reply;
import org.jeskey.dto.PageRequestDTO;
import org.jeskey.dto.PageResponseDTO;
import org.jeskey.dto.ReplyDTO;
import org.jeskey.repository.ReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

	private final ReplyRepository replyRepository;

	private final ModelMapper modelMapper;

	@Override
	public PageResponseDTO<ReplyDTO> getListReply(PageRequestDTO dto) {

		dto.setDisplay(30);

		//페이지가 -1이면 마지막 댓글페이지를 보여준다
		int totalCount = replyRepository.countByBoardBno(dto.getBno());
		int page = (dto.getPage() == -1) ? dto.getLastPage(totalCount) : dto.getPage();
		dto.setPage(page);

		Pageable pageable = PageRequest.of(page-1, dto.getDisplay(), Sort.by("rno").ascending());
		Page<Reply> result = replyRepository.findAllByBoardBno(dto.getBno(), pageable);

		List<ReplyDTO> replyList = result.getContent().stream().map(vo -> modelMapper.map(vo, ReplyDTO.class)).toList();

		return PageResponseDTO.<ReplyDTO>builder()
				.pageRequestDTO(dto)
				.dtoList(replyList)
				.total((int) result.getTotalElements())
				.build();
	}

	@Override
	public Long insertReply(ReplyDTO dto) {

		Reply reply = modelMapper.map(dto, Reply.class);

	  	replyRepository.save(reply);

	  	Long rno = reply.getRno();
	  	return rno;
	}

	@Override
	public void deleteReply(Long rno) {

		 replyRepository.deleteById(rno);
	}

	@Override
    public void deleteAll(Long bno){
        replyRepository.deleteAllByBoardBno(bno);
    }

	@Override
	public ReplyDTO getOneReply(Long rno) {

		 ReplyDTO replyDTO = modelMapper.map(replyRepository.findById(rno), ReplyDTO.class);

		 return replyDTO;
	 }
}
