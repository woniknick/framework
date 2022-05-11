package com.fwselect.framework;

import com.fwselect.data.SData;
import com.fwselect.util.FrameworkException;

public class Assembly {
	
	//정상 응답 데이터 조립
	public static SData assembly(SData commonData, Object resBizObj) {
		SData resData = new SData();
		//SData commonData = new SData(reqData.get("COMMONDATA"));
    	
		commonData.put("RESPONSETYPE", ErrorCode.NORMAL.getCode());
    	commonData.put("PRGNO", Integer.parseInt(commonData.get("PRGNO").toString())+1);
    	commonData.put("REQUESTTYPE", "R");
    	
    	resData.put("COMMONDATA", commonData);
    	resData.put("BIZDATA", resBizObj);

    	return resData;

	}
	
	//FrameworkException 에러 응답 데이터 조립
	public static SData assembly(SData commonData, FrameworkException e) {
		SData resData = new SData();
		//SData commonData = new SData(reqData.get("COMMONDATA"));
		
    	commonData.put("RESPONSETYPE", e.getErrorCode());
    	
    	commonData.put("PRGNO", Integer.parseInt(commonData.get("PRGNO").toString())+1);
    	commonData.put("REQUESTTYPE", "R");
    	
    	resData.put("COMMONDATA", commonData);
    	resData.put("BIZDATA", e.getErrorMsg());
    	
    	
    	return resData;
	}
	
	// 에러 응답 데이터 조립
	public static SData assembly(SData commonData, Exception e) {
		SData resData = new SData();
		//SData commonData = new SData(reqData.get("COMMONDATA"));
		
    	commonData.put("RESPONSETYPE", ErrorCode.ERROR.getCode());
    	
    	commonData.put("PRGNO", Integer.parseInt(commonData.get("PRGNO").toString())+1);
    	commonData.put("REQUESTTYPE", "R");
    	
    	resData.put("COMMONDATA", commonData);
    	resData.put("BIZDATA", ErrorCode.ERROR.getMsg());
    	
    	
    	return resData;
	}
	
}
