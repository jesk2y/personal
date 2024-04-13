package org.jeskey.service;

import org.jeskey.domain.Member;
import org.jeskey.dto.MemberDTO;

public interface MemberService {

	int insertMember(MemberDTO memberDTO);

	Member getMember(String user_id);

	int delMember(String user_id);

	int changeEmail(MemberDTO member);

	int changePassword(MemberDTO member);

}
