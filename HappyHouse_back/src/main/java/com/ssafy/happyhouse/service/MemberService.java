package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberService {

	public List<MemberDto> retrieveMember();
	public boolean checkId(String userId);
	public boolean registMember(MemberDto memberDto);
	public boolean updateMember(MemberDto memberDto);
	public boolean updateAuthMember(MemberDto memberDto);
	public boolean deleteMember(String userId);
	
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userId) throws Exception;
}
