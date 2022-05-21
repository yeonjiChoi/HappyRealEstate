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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.QnaBoardDto;
import com.ssafy.happyhouse.service.QnaBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("QnaBoardController V1")
@RestController
@RequestMapping("/qna")
public class QnaBoardController {

	private static final Logger logger = LoggerFactory.getLogger(QnaBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaBoardService qnaBoardService;

	@ApiOperation(value = "QnA 글목록", notes = "모든 QnA 글을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnaBoardDto>> retrieveQnaBoard() throws Exception {
		logger.debug("retrieveQnaBoard - 호출");
		return new ResponseEntity<List<QnaBoardDto>>(qnaBoardService.retrieveQnaBoard(), HttpStatus.OK);
	}

    @ApiOperation(value = "글번호에 해당하는 QnA게시글의 정보를 반환한다.", response = QnaBoardDto.class)    
	@GetMapping("{qnaNo}")
	public ResponseEntity<QnaBoardDto> detailQnaBoard(@PathVariable int qnaNo) {
		logger.debug("detailQnaBoard - 호출");
		return new ResponseEntity<QnaBoardDto>(qnaBoardService.detailQnaBoard(qnaNo), HttpStatus.OK);
	}
    
	
	@ApiOperation(value = "새로운 QnA게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQnaBoard(@RequestBody QnaBoardDto qnaBoardDto) {
		logger.debug("writeQnaBoard - 호출");
		if (qnaBoardService.writeQnaBoard(qnaBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
    @ApiOperation(value = "글번호에 해당하는 QnA게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{qnaNo}")
	public ResponseEntity<String> updateQnaBoard(@RequestBody QnaBoardDto qnaBoardDto) {
		logger.debug("updateQnaBoard - 호출");
		
		if (qnaBoardService.updateQnaBoard(qnaBoardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "글번호에 해당하는 QnA게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{qnaNo}")
	public ResponseEntity<String> deleteQnaBoard(@PathVariable int qnaNo) {
		logger.debug("deleteQnaBoard - 호출");
		if (qnaBoardService.deleteQnaBoard(qnaNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
