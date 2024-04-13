package org.jeskey.service;

import org.jeskey.common.PasswordEncoder;
import org.jeskey.domain.Member;
import org.jeskey.dto.MemberDTO;
import org.jeskey.mapper.MemberMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	private final MemberMapper memberMapper;

	@Override
	public int insertMember(MemberDTO memberDTO) {

		memberDTO.encodePassword(passwordEncoder.encrypt(memberDTO.getPassword()));
		Member member = modelMapper.map(memberDTO, Member.class);

		return memberMapper.insertMember(member);
	}

	@Override
	public Member getMember(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delMember(String user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeEmail(MemberDTO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changePassword(MemberDTO member) {
		// TODO Auto-generated method stub
		return 0;
	}
}
