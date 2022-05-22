package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "InterestAptDto : 관심 매물 정보", description = "사용자의 관심 매물 정보를 나타낸다")
public class InterestAptDto {
	@ApiModelProperty(value = "관심 매물 번호")
	private int no;
	@ApiModelProperty(value = "회원 아이디")
	private String userId;
	@ApiModelProperty(value = "아파트 거래 코드")
	private String aptNo;
	@ApiModelProperty(value = "관심 매물 설정일")
	private String date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "InterestAptDto [no=" + no + ", userId=" + userId + ", aptNo=" + aptNo + ", date=" + date + "]";
	}
	
}
