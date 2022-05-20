package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.JwtServiceImpl;
import com.ssafy.happyhouse.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private MemberService memberService;
	
	@ApiOperation(value = "회원 전체 목록", notes = "모든 회원의 정보를 반환한다.", response=List.class)
	@GetMapping
	public ResponseEntity<List<MemberDto>> retrieveMember() {
		logger.debug("retrieveMember 호출");
		return new ResponseEntity<List<MemberDto>>(memberService.retrieveMember(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아이디 중복 검사", notes = "아이디를 받아 해당 아이디가 사용 가능한지 판단. 사용 가능하면 'success', 사용 불가능하면 'fail'을 반환한다.", response=String.class)
	@GetMapping("checkId/{userId}")
	public ResponseEntity<String> checkId(@PathVariable String userId) {
		logger.debug("checkId 호출");
		if(memberService.checkId(userId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 등록", notes = "회원 정보를 받아 회원 가입을 수행한다(아이디, 비밀번호, 이름, 이메일 필요). 성공 여부에 따라 'success' 또는 'fail'을 반환한다.", response=String.class)
	@PostMapping
	public ResponseEntity<String> registMember(@RequestBody @ApiParam(value = "회원가입 시 필요한 회원정보(아이디, 비밀번호, 이름, 이메일).", required = true) MemberDto memberDto) {
		logger.debug("registMember 호출");
		if(memberService.registMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 정보수정", notes = "회원 정보 수정을 수행한다(아이디, 이름, 이메일 필요). 성공 여부에 따라 'success' 또는 'fail'을 반환한다.", response=String.class)
	@PutMapping("{userId}")
	public ResponseEntity<String> updateMember(@PathVariable  @ApiParam(value = "수정할 회원의 아이디.", required = true) String userId, @RequestBody MemberDto memberDto) {
		logger.debug("updateMember 호출");
		if(memberService.updateMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 정보 권한 수정", notes = "관리자에 의해 회원 권한 수정을 수행한다. 성공 여부에 따라 'success' 또는 'fail'을 반환한다.", response=String.class)
	@PutMapping("auth/{userId}")
	public ResponseEntity<String> updateAuthMember(@PathVariable @ApiParam(value = "수정할 회원의 아이디.", required = true) String userId, @RequestBody MemberDto memberDto) {
		logger.debug("updateAuthMember 호출");
		if(memberService.updateAuthMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 삭제", notes = "회원 삭제를 수행한다(아이디). 성공 여부에 따라 'success' 또는 'fail'을 반환한다.", response=String.class)
	@DeleteMapping("{userId}")
	public ResponseEntity<String> deleteMember(@PathVariable  @ApiParam(value = "삭제할 회원의 아이디.", required = true) String userId) {
		logger.debug("deleteMember 호출");
		if(memberService.deleteMember(userId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId, HttpServletRequest request) {
		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userId);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
