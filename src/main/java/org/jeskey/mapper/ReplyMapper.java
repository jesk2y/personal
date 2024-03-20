package org.jeskey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeskey.domain.Reply;

@Mapper
public interface ReplyMapper {

	List<Reply> getListReply(Long bno);

	Reply getOneReply(Long rno);

	int insertReply(Reply reply);

	int updateReply(Reply reply);

	int deleteReply(Long bno);
}
