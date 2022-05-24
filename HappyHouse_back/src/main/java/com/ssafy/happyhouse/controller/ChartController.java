package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.ChartDto;
import com.ssafy.happyhouse.service.ChartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("ChartController V1")
@RestController
@RequestMapping("/chart")
public class ChartController {
	private static final Logger logger = LoggerFactory.getLogger(ChartController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ChartService chartService;
	
	@ApiOperation(value = "차트 데이터 반환", notes = "차트 데이터를 반환한다.", response=List.class)
	@GetMapping
	public ResponseEntity<List<ChartDto>> getChartData() throws Exception {
		logger.debug("getChartData 호출");
		return new ResponseEntity<List<ChartDto>>(chartService.getChartData(), HttpStatus.OK);
		
	}
}
