package org.jeskey.service;

import java.util.Optional;

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
	public MemberDTO getMember(String user_id) {

		Optional<Member> result = Optional.ofNullable(memberMapper.getMember(user_id));

		if(result.isEmpty()) {

			//에러발생
			return null;
		}

		MemberDTO dto = modelMapper.map(result.get(), MemberDTO.class);

		return dto;
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
