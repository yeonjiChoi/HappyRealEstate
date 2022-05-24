package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunDongCodeDto;

@Mapper
public interface HouseMapMapper {
	List<SidoGugunDongCodeDto> getSido() throws SQLException;
	List<SidoGugunDongCodeDto> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunDongCodeDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getDealInAPt(String aptCode);
	List<HouseDto> getAptInDongPagination(Map<String, Object> map);
	Integer getTotalAptCount(String dongCode);
}
