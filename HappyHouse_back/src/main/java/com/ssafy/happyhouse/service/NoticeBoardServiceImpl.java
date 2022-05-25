package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.NoticeBoardDto;
import com.ssafy.happyhouse.mapper.NoticeBoardMapper;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

	@Autowired
	private NoticeBoardMapper noticeBoardMapper;
	
	@Override
	public List<NoticeBoardDto> retrieveNoticeBoard(Map<String, String> map) {
		Map<String, String> param = new HashMap<String, String>();
		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		param.put("value", map.get("value") == null ? "" : map.get("value"));
		return noticeBoardMapper.selectNoticeBoard(param);
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
