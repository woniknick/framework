package com.fwselect.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fwselect.data.SData;

public class Separation {
	private static Logger logger = LoggerFactory.getLogger(Validation.class);
	
	// 공통헤더 분리
	public static SData separationCommon(SData reqData) {
		SData commonData = new SData(reqData.get("COMMONDATA"));
		logger.info("separationCommon" + commonData);
		return commonData;
		
	}
	
	// Biz 데이터부 분리
	public static SData separationBiz(SData reqData) {
		SData bizData = new SData(reqData.get("BIZDATA"));
		logger.info("separationBiz" + bizData);
		return bizData;
		
	}
}
