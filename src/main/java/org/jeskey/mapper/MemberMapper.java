package org.jeskey.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.jeskey.domain.Member;

@Mapper
public interface MemberMapper {

	int insertMember(Member member);

	Member getMember(String user_id);

	int delMember(String user_id);

	int changeEmail(Member member);

	int changePassword(Member member);
}
