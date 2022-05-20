package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public List<MemberDto> selectMember();
	public int selectId(String userId);
	public int insertMember(MemberDto memberDto);
	public int updateMember(MemberDto memberDto);
	public int deleteMember(String userId);
	public int updateAuthMember(MemberDto memberDto);
	public MemberDto login(MemberDto memberDto) throws SQLException ;
	public MemberDto userInfo(String userId)  throws SQLException;
	
}
