package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.QnaCommentDto;

public interface QnaCommentService {

	List<QnaCommentDto> retrieveQnaComment(int qnaNo);

	boolean writeQnaComment(QnaCommentDto qnaCommentDto);
	boolean updateQnaComment(QnaCommentDto qnaCommentDto);

	boolean deleteQnaComment(int no);

}
