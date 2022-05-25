package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.NoticeBoardDto;
import com.ssafy.happyhouse.service.NoticeBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("NoticeBoardController V1")
@RestController
@RequestMapping("/notice")
public class NoticeBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeBoardService noticeBoardService;
	
    @ApiOperation(value = "공지사항 글목록", notes = "모든 공지사항 글을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeBoardDto>> retrieveNoticeBoard(@RequestParam Map<String, String> map) throws Exception {
    	logger.debug("retrieveNoticeBoard - 호출 {}", map);
		return new ResponseEntity<List<NoticeBoardDto>>(noticeBoardService.retrieveNoticeBoard(map), HttpStatus.OK);
	}
	
	
    @ApiOperation(value = "공지사항 글보기", notes = "글번호에 해당하는 공지사항 글의 정보를 반환한다.", response = NoticeBoardDto.class)    
	@GetMapping("{noticeNo}")
	public ResponseEntity<NoticeBoardDto> detailNoticeBoard(@PathVariable int noticeNo) {
		logger.debug("detailNoticeBoard - 호출");
		return new ResponseEntity<NoticeBoardDto>(noticeBoardService.detailNoticeBoard(noticeNo), HttpStatus.OK);
	}
    
    @ApiOperation(value = "공지사항 글등록", notes = "새로운 공지사항글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNoticeBoard(@RequestBody NoticeBoardDto noticeBoardDto) {
		logger.debug("writeNoticeBoard - 호출");
		if (noticeBoardService.writeNoticeBoard(noticeBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "공지사항 글수정", notes = "글번호에 해당하는 공지사항 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{noticeNo}")
	public ResponseEntity<String> updateNoticeBoard(@RequestBody NoticeBoardDto noticeBoardDto) {
		logger.debug("updateNoticeBoard - 호출");
		
		if (noticeBoardService.updateNoticeBoard(noticeBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "공지사항 글삭제", notes = "글번호에 해당하는 공지사항 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{noticeNo}")
	public ResponseEntity<String> deleteNoticeBoard(@PathVariable int noticeNo) {
		logger.debug("deleteNoticeBoard - 호출");
		if (noticeBoardService.deleteNoticeBoard(noticeNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
