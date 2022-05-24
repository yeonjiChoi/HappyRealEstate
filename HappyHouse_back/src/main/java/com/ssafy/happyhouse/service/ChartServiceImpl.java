package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.ChartDto;
import com.ssafy.happyhouse.mapper.ChartMapper;

@Service
public class ChartServiceImpl implements ChartService {
	
	@Autowired
	private ChartMapper chartMapper;
	@Override
	public List<ChartDto> getChartData() {
		return chartMapper.selectData();
	}

}
