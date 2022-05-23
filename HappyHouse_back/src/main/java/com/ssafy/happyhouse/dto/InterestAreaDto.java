package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "InterestAreaDto : 관심 지역 정보", description = "사용자의 관심 지역 정보를 나타낸다")
public class InterestAreaDto {
	@ApiModelProperty(value = "관심 지역 번호")
	private int no;
	@ApiModelProperty(value = "회원 아이디")
	private String userId;
	@ApiModelProperty(value = "동 코드")
	private String dongCode;
	@ApiModelProperty(value = "관심 지역 설정일")
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
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "InterestAreaDto [no=" + no + ", userId=" + userId + ", dongCode=" + dongCode + ", date=" + date + "]";
	}
	
}
