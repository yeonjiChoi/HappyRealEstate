package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.ChartDto;

@Mapper
public interface ChartMapper {

	List<ChartDto> selectData();

}
