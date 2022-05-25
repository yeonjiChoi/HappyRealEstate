package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.happyhouse.dto.QnaCommentDto;

@Mapper
public interface QnaCommentMapper {

	List<QnaCommentDto> selectQnaComment(int qnaNo);

	int insertQnaComment(QnaCommentDto qnaCommentDto);

	int updateQnaComment(QnaCommentDto qnaCommentDto);

	int deleteQnaComment(int no);


}
