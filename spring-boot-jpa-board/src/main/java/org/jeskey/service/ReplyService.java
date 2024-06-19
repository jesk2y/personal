package org.jeskey.service;

import org.jeskey.dto.PageRequestDTO;
import org.jeskey.dto.PageResponseDTO;
import org.jeskey.dto.ReplyDTO;

public interface ReplyService {

	public PageResponseDTO<ReplyDTO> getListReply(PageRequestDTO page);

	public Long insertReply(ReplyDTO reply);

	public void deleteReply(Long bno);

	public ReplyDTO getOneReply(Long rno);

	public void deleteAll(Long bno);
}
