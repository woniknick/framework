package com.fwselect.util;

import com.fwselect.framework.ErrorCode;

public class FrameworkException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMsg;
	
	
	public FrameworkException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}


	public FrameworkException(ErrorCode errorCode) {
		this.errorCode = errorCode.getCode();
		this.errorMsg = errorCode.getMsg();
	}


	public String getErrorCode() {
		return errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}
	
	
	

}
