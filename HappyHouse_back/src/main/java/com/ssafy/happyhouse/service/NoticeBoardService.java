package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.NoticeBoardDto;

public interface NoticeBoardService {

	List<NoticeBoardDto> retrieveNoticeBoard(Map<String, String> map);
	NoticeBoardDto detailNoticeBoard(int noticeNo);
	boolean writeNoticeBoard(NoticeBoardDto noticeBoardDto);
	boolean updateNoticeBoard(NoticeBoardDto noticeBoardDto);
	boolean deleteNoticeBoard(int noticeNo);

}
