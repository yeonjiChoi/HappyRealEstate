package com.ssafy.happyhouse.dto;

public class AptSearchDto {
	private int limit;
	private int offset;
	private String dongCode;
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	@Override
	public String toString() {
		return "AptSearchDto [limit=" + limit + ", offset=" + offset + ", dongCode=" + dongCode + "]";
	}
	
}
