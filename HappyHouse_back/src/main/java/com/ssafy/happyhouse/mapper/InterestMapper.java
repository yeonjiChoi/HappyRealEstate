package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BaseAddressDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.InterestAptDto;
import com.ssafy.happyhouse.dto.InterestAreaDto;

@Mapper
public interface InterestMapper {

	int insertInterestArea(InterestAreaDto interestAreaDto);
	int checkInterestArea(InterestAreaDto interestAreaDto);
	List<BaseAddressDto> selectInterestArea(String userId);
	int deleteInterestArea(InterestAreaDto interestAreaDto);
	
	int checkInterestApt(InterestAptDto interestAptDto);
	int insertInterestApt(InterestAptDto interestAptDto);
	List<HouseInfoDto> selectInterestApt(String userId);
	int deleteInterestApt(InterestAptDto interestAptDto);

}
