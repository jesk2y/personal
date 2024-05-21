package org.jeskey.service;

import java.util.List;

import org.jeskey.dto.PageDTO;
import org.jeskey.dto.ReplyDTO;

public interface ReplyService {

	public List<ReplyDTO> getListReply(PageDTO page);

	public Long insertReply(ReplyDTO reply);

	public void deleteReply(Long bno);

	public ReplyDTO getOneReply(Long rno);
}
