package com.fwselect.framework;

//업무코드 관리
public enum BizCode {
	FWBIZCM("CM"),
	FWBIZAC("AC"),
	FWBIZAQ("AQ"),
	FWBIZBP("BP");
	
	private String code;
	
	private BizCode(String code) {
		this.code = code;
	}
	
	public static BizCode getBizCode(String code) {
		for(BizCode bc : BizCode.values()) {
			if(bc.getCode().equals(code)) {
				return bc;
			}
		}
		return null;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
