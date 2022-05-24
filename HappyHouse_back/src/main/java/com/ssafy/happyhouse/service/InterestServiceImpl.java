package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.BaseAddressDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.InterestAptDto;
import com.ssafy.happyhouse.dto.InterestAreaDto;
import com.ssafy.happyhouse.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private InterestMapper interestMapper;
	
	@Override
	public boolean registInterestArea(InterestAreaDto interestAreaDto) {
		// 관심 지역으로 등록되어 있는지 확인 -> 리턴값이 1이면 이미 등록된 관심 지역
		if(interestMapper.checkInterestArea(interestAreaDto) == 1) {
			return false;
		} else {
			return interestMapper.insertInterestArea(interestAreaDto) == 1;
		}
	}

	@Override
	public List<BaseAddressDto> retrieveInterestArea(String userId) {
		return interestMapper.selectInterestArea(userId);
	}

	@Override
	public boolean deleteInterestArea(int no) {
		return interestMapper.deleteInterestArea(no) == 1;
	}

	@Override
	public boolean registInterestApt(InterestAptDto interestAptDto) {
		if(interestMapper.checkInterestApt(interestAptDto) == 1) {
			return false;
		} else {
			return interestMapper.insertInterestApt(interestAptDto) == 1;
		}
	}

	@Override
	public List<HouseInfoDto> retrieveInterestApt(String userId) {
		return interestMapper.selectInterestApt(userId);
	}

	@Override
	public boolean deleteInterestApt(int no) {
		return interestMapper.deleteInterestApt(no) == 1;
	}

}
