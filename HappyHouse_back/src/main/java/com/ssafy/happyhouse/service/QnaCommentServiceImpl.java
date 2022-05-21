package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.QnaCommentDto;
import com.ssafy.happyhouse.mapper.QnaCommentMapper;

@Service
public class QnaCommentServiceImpl implements QnaCommentService {

	@Autowired
	private QnaCommentMapper qnaCommentMapper;
	
	@Override
	public List<QnaCommentDto> retrieveQnaComment(int qnaNo) {
		return qnaCommentMapper.selectQnaComment(qnaNo);
	}

	@Override
	public boolean writeQnaComment(QnaCommentDto qnaCommentDto) {
		return qnaCommentMapper.insertQnaComment(qnaCommentDto) == 1;
	}

	@Override
	public boolean updateQnaComment(QnaCommentDto qnaCommentDto) {
		return qnaCommentMapper.updateQnaComment(qnaCommentDto) == 1;
	}

	@Override
	public boolean deleteQnaComment(int no) {
		return qnaCommentMapper.deleteQnaComment(no) == 1;
	}

}
