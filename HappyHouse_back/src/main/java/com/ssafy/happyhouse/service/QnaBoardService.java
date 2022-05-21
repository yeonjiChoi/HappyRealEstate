package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.QnaBoardDto;

public interface QnaBoardService {

	List<QnaBoardDto> retrieveQnaBoard();
	QnaBoardDto detailQnaBoard(int qnaNo);
	boolean writeQnaBoard(QnaBoardDto qnaBoardDto);
	boolean updateQnaBoard(QnaBoardDto qnaBoardDto);
	boolean deleteQnaBoard(int qnaNo);

}
