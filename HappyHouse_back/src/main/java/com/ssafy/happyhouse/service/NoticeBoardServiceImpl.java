package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.NoticeBoardDto;
import com.ssafy.happyhouse.mapper.NoticeBoardMapper;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

	@Autowired
	private NoticeBoardMapper noticeBoardMapper;
	
	@Override
	public List<NoticeBoardDto> retrieveNoticeBoard() {
		return noticeBoardMapper.selectNoticeBoard();
	}

	@Override
	public boolean writeNoticeBoard(NoticeBoardDto noticeBoardDto) {
		return noticeBoardMapper.insertNoticeBoard(noticeBoardDto) == 1;
	}

	@Override
	public NoticeBoardDto detailNoticeBoard(int noticeNo) {
		return noticeBoardMapper.selectNoticeBoardByNo(noticeNo);
	}

	@Override
	public boolean updateNoticeBoard(NoticeBoardDto noticeBoardDto) {
		return  noticeBoardMapper.updateNoticeBoard(noticeBoardDto) == 1;
	}

	@Override
	public boolean deleteNoticeBoard(int noticeNo) {
		return noticeBoardMapper.deleteNoticeBoard(noticeNo) == 1;
	}

}
