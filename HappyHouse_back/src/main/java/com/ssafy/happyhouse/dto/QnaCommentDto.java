package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnaCommentDto (QnA게시글 댓글 정보)", description = "댓글 번호, 글 번호, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class QnaCommentDto {
	@ApiModelProperty(value = "댓글 번호")
	private int no;
	@ApiModelProperty(value = "글 번호")
	private int qnaNo;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "QnaCommentDto [no=" + no + ", qnaNo=" + qnaNo + ", userId=" + userId + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
	
	

}
