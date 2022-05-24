package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.AptSearchDto;
import com.ssafy.happyhouse.dto.HouseDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunDongCodeDto;

public interface HouseMapService {

	List<SidoGugunDongCodeDto> getSido() throws Exception;
	List<SidoGugunDongCodeDto> getGugunInSido(String sido) throws Exception;
	List<SidoGugunDongCodeDto> getDongInGugun(String gugun) throws Exception;
	
	List<HouseDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getDealInAPt(String aptCode);
	List<HouseDto> getAptInDongPage(Map<String, Object> map);
	Integer getTotalAptCount(String dongCode);
	
}
