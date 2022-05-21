package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.NoticeBoardDto;

@Mapper
public interface NoticeBoardMapper {

	List<NoticeBoardDto> selectNoticeBoard();
	NoticeBoardDto selectNoticeBoardByNo(int noticeNo);
	int insertNoticeBoard(NoticeBoardDto noticeBoardDto);
	int updateNoticeBoard(NoticeBoardDto noticeBoardDto);
	int deleteNoticeBoard(int noticeNo);


}
