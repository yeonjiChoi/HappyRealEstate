package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public List<MemberDto> retrieveMember() {
		return memberMapper.selectMember();
	}
	@Override
	public boolean checkId(String userId) {
		// 해당 아이디가 사용 중이면 1 리턴, 사용 중이지 않으면 0 리턴
		return memberMapper.selectId(userId) == 0;
	}

	@Override
	public boolean registMember(MemberDto memberDto) {
		return memberMapper.insertMember(memberDto) == 1;
	}
	
	@Override
	public boolean updateMember(MemberDto memberDto) {
		return memberMapper.updateMember(memberDto) == 1;
	}
	
	@Override
	public boolean updateAuthMember(MemberDto memberDto) {
		return memberMapper.updateAuthMember(memberDto) == 1;
	}
	
	@Override
	public boolean deleteMember(String userId) {
		return memberMapper.deleteMember(userId) == 1;
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserId() == null || memberDto.getUserPwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}

}
