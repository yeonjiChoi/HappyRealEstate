package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.QnaBoardDto;

@Mapper
public interface QnaBoardMapper {

	List<QnaBoardDto> selectQnaBoard();
	int insertQnaBoard(QnaBoardDto qnaBoardDto);
	QnaBoardDto selectQnaBoardByNo(int qnaNo);
	int updateQnaBoard(QnaBoardDto qnaBoardDto);
	int deleteQnaBoard(int qnaNo);

}
