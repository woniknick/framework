package com.fwselect.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fwselect.data.SData;
import com.fwselect.util.FrameworkException;

public class Validation {
	private static Logger logger = LoggerFactory.getLogger(Validation.class);
	
	public static void validation(SData reqData) {
		SData commonData = new SData(reqData.get("COMMONDATA"));
		
		//(homework) 검증별 에러코드 분류
		try {
			// 공통헤더 존재 여부 검증
			if(!reqData.containsKey("COMMONDATA")) {
				logger.error("헤더부 없음!" + reqData.toString());
				throw new FrameworkException(ErrorCode.ERROR);
			}
			
			// 공통헤더 길이 검증
			if(commonData.get("GUID").toString().length() != 31) {
				logger.error("GUID 길이 에러 : " + reqData.toString());
				throw new FrameworkException(ErrorCode.ERROR);
			}
			
			//프레임워크 등록 업무코드 검증
			if(BizCode.getBizCode(commonData.get("GUID").toString().substring(22, 24)) == null) {
				logger.error("업무코드 검증 에러" + reqData.toString());
				throw new FrameworkException(ErrorCode.ERROR);
			}
			
			//거래시간 길이 에러
			if(commonData.get("TRXDATETIME").toString().length() != 14) {
				logger.error("거래시간 길이 에러 : " + reqData.toString());
				throw new FrameworkException(ErrorCode.ERROR);
			}
			
			//전문진행번호 검증
			if(commonData.get("PRGNO") == null) {
				logger.error("전문진행번호 미입력 : " + reqData.toString());
				throw new FrameworkException(ErrorCode.ERROR);
			}
			
			//요청구분 코드 검증
			if(!(commonData.get("REQUESTTYPE").toString().equals("S"))) {
				logger.error("요청구분 코드값 에러 " + reqData.toString());
				throw new FrameworkException(ErrorCode.ERROR);
			}
			

		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Validation 클래스 실행 에러");
			throw new FrameworkException(ErrorCode.NODATA);

		}
	}
}
