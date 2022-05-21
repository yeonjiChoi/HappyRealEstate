package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.QnaBoardDto;
import com.ssafy.happyhouse.mapper.QnaBoardMapper;

@Service
public class QnaBoardServiceImpl implements QnaBoardService {

	@Autowired
	private QnaBoardMapper qnaBoardMapper;
	
	@Override
	public List<QnaBoardDto> retrieveQnaBoard() {
		return qnaBoardMapper.selectQnaBoard();
	}

	@Override
	public QnaBoardDto detailQnaBoard(int qnaNo) {
		return qnaBoardMapper.selectQnaBoardByNo(qnaNo);
	}
	
	@Override
	public boolean writeQnaBoard(QnaBoardDto qnaBoardDto) {
		return qnaBoardMapper.insertQnaBoard(qnaBoardDto) == 1;
	}

	@Override
	public boolean updateQnaBoard(QnaBoardDto qnaBoardDto) {
		return qnaBoardMapper.updateQnaBoard(qnaBoardDto) == 1;
	}

	@Override
	public boolean deleteQnaBoard(int qnaNo) {
		return qnaBoardMapper.deleteQnaBoard(qnaNo) == 1;
	}

}
