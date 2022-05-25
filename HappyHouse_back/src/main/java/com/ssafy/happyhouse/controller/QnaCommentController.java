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

import com.ssafy.happyhouse.dto.QnaCommentDto;
import com.ssafy.happyhouse.service.QnaCommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/qna/comment")
public class QnaCommentController {

	private static final Logger logger = LoggerFactory.getLogger(QnaCommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaCommentService qnaCommentService;
	
	@ApiOperation(value = " QnA게시글 중 qnaNo에 해당하는 댓글을 모두 반환한다.", response = List.class)
	@GetMapping("{qnaNo}")
	public ResponseEntity<List<QnaCommentDto>> retrieveQnaComment(@PathVariable int qnaNo) throws Exception {
		logger.debug("retrieveQnaComment - 호출");
		return new ResponseEntity<List<QnaCommentDto>>(qnaCommentService.retrieveQnaComment(qnaNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "QnA게시글에 댓글을 등록한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQnaComment(@RequestBody QnaCommentDto qnaCommentDto) {
		logger.debug("writeQnaComment - 호출");
		if (qnaCommentService.writeQnaComment(qnaCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "QnA게시글의 댓글을 수정한다..", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateQnaComment(@RequestBody QnaCommentDto qnaCommentDto) {
		logger.debug("updateQnaComment - 호출 {}", qnaCommentDto);
		if (qnaCommentService.updateQnaComment(qnaCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
    @ApiOperation(value = "QnA게시글의 댓글을 삭제한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteQnaComment(@PathVariable int no) {
		logger.debug("deleteQnaComment - 호출");
		if (qnaCommentService.deleteQnaComment(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
