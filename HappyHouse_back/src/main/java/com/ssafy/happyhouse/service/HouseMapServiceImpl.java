package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.AptSearchDto;
import com.ssafy.happyhouse.dto.HouseDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunDongCodeDto;
import com.ssafy.happyhouse.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunDongCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunDongCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunDongCodeDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getDealInAPt(String aptCode) {
		return houseMapMapper.getDealInAPt(aptCode);
	}

	@Override
	public List<HouseDto> getAptInDongPage(Map<String, Object> map) {
		return houseMapMapper.getAptInDongPagination(map);
	}

	@Override
	public Integer getTotalAptCount(String dongCode) {
		return houseMapMapper.getTotalAptCount(dongCode);
	}

}
