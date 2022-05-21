package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnaBoardDto (QnA 정보)", description = "글번호, 제목, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class QnaBoardDto {
	@ApiModelProperty(value = "글번호")
	private int qnaNo;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "작성일")
	private String regDate;
	
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "QnaBoardDto [qnaNo=" + qnaNo + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", regDate=" + regDate + "]";
	}
	
}
