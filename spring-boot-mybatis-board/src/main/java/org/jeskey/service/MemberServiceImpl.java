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

		String hashedPw = passwordEncoder.encrypt(memberDTO.getPassword());	//비밀번호 암호화
		memberDTO.hashedPassword(hashedPw);	//암호화된 비밀번호 저장

		Member member = modelMapper.map(memberDTO, Member.class);

		return memberMapper.insertMember(member);
	}

	@Override
	public MemberDTO getMember(String user_id) {

		Optional<Member> result = Optional.ofNullable(memberMapper.getMember(user_id));

		if(result.isEmpty()) {	//아이디가 존재하지 않으면

			return null;
		}

		MemberDTO dto = modelMapper.map(result.get(), MemberDTO.class);

		return dto;
	}

	@Override
	public int delMember(String user_id) {

		return memberMapper.delMember(user_id);
	}

	@Override
	public int changeEmail(MemberDTO memberDTO) {

		int i = memberMapper.changeEmail(modelMapper.map(memberDTO, Member.class));

		return i;
	}

	@Override
	public int changePassword(MemberDTO memberDTO) {

		memberDTO.hashedPassword(passwordEncoder.encrypt(memberDTO.getPassword()));//비밀번호 암호화

		int i = memberMapper.changePassword(modelMapper.map(memberDTO, Member.class));

		return i;
	}
}
