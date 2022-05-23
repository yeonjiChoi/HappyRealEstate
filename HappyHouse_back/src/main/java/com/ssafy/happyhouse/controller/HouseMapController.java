package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunDongCodeDto;
import com.ssafy.happyhouse.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("HouseMapController V1")
@RestController
@RequestMapping("/map")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	@Autowired
	private HouseMapService houseMapService;
	
	@ApiOperation(value = "전체 시도 반환", notes = "모든 시도 목록을 반환한다.", response=List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDongCodeDto>> sido() throws Exception {
//		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "전체 구군 반환", notes = "모든 구군 목록을 반환한다.", response=List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDongCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
//		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "전체 동 반환", notes = "모든 동 목록을 반환한다.", response=List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunDongCodeDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SidoGugunDongCodeDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 정보 반환", notes = "동코드에 해당하는 모든 아파트 정보를 반환한다.", response=List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseDto>> apt(@RequestParam("dong") String dong) throws Exception {
		logger.debug("apt 호출 - dongCode : {}", dong);
		return new ResponseEntity<List<HouseDto>>(houseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 매매 정보 반환", notes = "아파트 코드에 해당하는 모든 아파트 매매 정보를 반환한다.", response=List.class)
	@GetMapping("/deal")
	public ResponseEntity<List<HouseInfoDto>> deal(@RequestParam("aptCode") String aptCode) throws Exception {
		logger.debug("deal 호출 - aptCode : {}", aptCode);
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDealInAPt(aptCode), HttpStatus.OK);
	}
}
