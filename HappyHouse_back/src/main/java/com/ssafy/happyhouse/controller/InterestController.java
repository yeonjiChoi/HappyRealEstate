package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BaseAddressDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.InterestAptDto;
import com.ssafy.happyhouse.dto.InterestAreaDto;
import com.ssafy.happyhouse.service.InterestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("InterestController V1")
@RestController
@RequestMapping("/interest")
public class InterestController {

	public static final Logger logger = LoggerFactory.getLogger(InterestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private InterestService interestService;

	@ApiOperation(value = "관심 지역 등록", notes = "회원 아이디와 동 코드를 받아 관심 지역으로 등록한다. 성공 여부에 따라 'success' 또는 'fail'을 반환한다.", response=String.class)
	@PostMapping("/area")
	public ResponseEntity<String> registInterestArea(@RequestBody InterestAreaDto interestAreaDto) {
		logger.debug("registInterestArea 호출");
		if(interestService.registInterestArea(interestAreaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "관심 지역 정보 반환", notes = "회원 아이디를 받아 회원이 등록한 관심 지역 정보를 반환한다.", response=List.class)
	@GetMapping("/area/{userId}")
	public ResponseEntity<List<BaseAddressDto>> retrieveInterestArea(@PathVariable("userId") String userId) {
		logger.debug("retrieveInterestArea 호출");
		return new ResponseEntity<List<BaseAddressDto>>(interestService.retrieveInterestArea(userId), HttpStatus.OK);
	} 

	@ApiOperation(value = "관심 지역 해제", notes = "회원이 등록한 관심 지역 정보를 해제한다.", response=String.class)
	@DeleteMapping("/area")
	public  ResponseEntity<String> deleteInterestArea(@RequestBody InterestAreaDto interestAreaDto) {
		logger.debug("deleteInterestArea - 호출");
		if (interestService.deleteInterestArea(interestAreaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심 매물 등록", notes = "회원 아이디와 아파트 거래 코드를 받아 관심 지역으로 등록한다. 성공 여부에 따라 'success' 또는 'fail'을 반환한다.", response=String.class)
	@PostMapping("/apt")
	public ResponseEntity<String> registInterestApt(@RequestBody InterestAptDto interestAptDto) {
		logger.debug("registInterestApt 호출");
		if(interestService.registInterestApt(interestAptDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심 아파트 매물 정보 반환", notes = "회원 아이디를 받아 회원이 등록한 관심 아파트 매물 정보를 반환한다.", response=List.class)
	@GetMapping("/apt/{userId}")
	public ResponseEntity<List<HouseInfoDto>> retrieveInterestApt(@PathVariable("userId") String userId) {
		logger.debug("retrieveInterestApt 호출");
		return new ResponseEntity<List<HouseInfoDto>>(interestService.retrieveInterestApt(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심 아파트 매물 해제", notes = "회원이 등록한 관심 아파트 매물 정보를 해제한다.", response=String.class)
	@DeleteMapping("/apt")
	public ResponseEntity<String> deleteInterestApt(@RequestBody @ApiParam(value = "아파트 매물 코드인 aptNo(아파트 번호 no 아님)와 회원 아이디 전달", required = true)  InterestAptDto interestAptDto) {
		logger.debug("deleteInterestApt - 호출");
		if (interestService.deleteInterestApt(interestAptDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
