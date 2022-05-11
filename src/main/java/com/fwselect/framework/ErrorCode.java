package com.fwselect.framework;

//에러코드 관리
public enum ErrorCode {
	NORMAL("NM", "Normal"),
	NODATA("ND", "No Biz Data"),
	ERROR("ER", "Error");
	
	private String code;
	private String msg;
	
	private ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	
}