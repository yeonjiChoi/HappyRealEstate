package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.BaseAddressDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.InterestAptDto;
import com.ssafy.happyhouse.dto.InterestAreaDto;

public interface InterestService {

	boolean registInterestArea(InterestAreaDto interestAreaDto);
	List<BaseAddressDto> retrieveInterestArea(String userId);
	boolean deleteInterestArea(int no);
	boolean registInterestApt(InterestAptDto interestAptDto);
	List<HouseInfoDto> retrieveInterestApt(String userId);
	boolean deleteInterestApt(InterestAptDto interestAptDto);

}
