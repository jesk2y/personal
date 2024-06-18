package org.jeskey.service;

import java.util.List;

import org.jeskey.dto.PageRequestDTO;
import org.jeskey.dto.ReplyDTO;

public interface ReplyService {

	public List<ReplyDTO> getListReply(PageRequestDTO page);

	public Long insertReply(ReplyDTO reply);

	public void deleteReply(Long bno);

	public ReplyDTO getOneReply(Long rno);
}
