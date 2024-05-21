package org.jeskey.service;

import org.jeskey.dto.MemberDTO;

public interface MemberService {

	int insertMember(MemberDTO memberDTO);

	MemberDTO getMember(String user_id);

	int delMember(String user_id);

	int changeEmail(MemberDTO member);

	int changePassword(MemberDTO member);

}
