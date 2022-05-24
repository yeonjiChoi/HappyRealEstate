package com.ssafy.happyhouse.dto;

public class ChartDto {
	private String dongCode;
	private String dongName;
	private int count;
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ChartDto [dongCode=" + dongCode + ", dongName=" + dongName + ", count=" + count + "]";
	}
	
	
}
