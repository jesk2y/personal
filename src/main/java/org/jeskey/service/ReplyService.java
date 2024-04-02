package org.jeskey.service;

import java.util.List;

import org.jeskey.dto.PageDTO;
import org.jeskey.dto.ReplyDTO;

public interface ReplyService {

	public List<ReplyDTO> getListReply(PageDTO page);

	public ReplyDTO getOneReply(Long rno);

	public Long insertReply(ReplyDTO reply);

	public void deleteReply(Long bno);
}
