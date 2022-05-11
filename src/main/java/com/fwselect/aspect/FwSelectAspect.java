package com.fwselect.aspect;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fwselect.controller.FrameworkController;
import com.fwselect.data.SData;
import com.fwselect.data.SMultiData;
import com.fwselect.framework.Assembly;
import com.fwselect.framework.ErrorCode;
import com.fwselect.framework.Parsing;
import com.fwselect.framework.Separation;
import com.fwselect.framework.Validation;
import com.fwselect.util.FrameworkException;

@Aspect
@Component
public class FwSelectAspect {
	private static Logger logger = LoggerFactory.getLogger(FwSelectAspect.class);
	
	@Autowired
	private FrameworkController frameworkController;
	
	@Pointcut("execution(* com.fwselect.controller.*BizRestController.*(..))")
	public void bizFramework() { }
	
	@Around("bizFramework()")
	public Object fwSelect(ProceedingJoinPoint joinPoint) throws Throwable {

	    logger.info("Framework 호출 ");		
	    
	    SData reqData = new SData();
	    SData commonData = new SData();
	    SData reqBizData = new SData();
	    SData resData = new SData();
	    Object resBizObj = new Object();
	    String resDateTime = new String();
	    	    
	    try {
		    //Request Data 파싱(SData)
			reqData = Parsing.parsing(joinPoint);
		    
			logger.info("프레임워크 파싱 데이터 확인 : " + reqData);
			
	    } catch(Exception e){
	    	logger.error("입력데이터 Parsing 에러" + e);
        	throw new FrameworkException(ErrorCode.ERROR);
	    }

		
	    try {
	    	//Request Data 검증
		    Validation.validation(reqData);
		    
		    //Request Data 분리(공통부 + 데이터부)
		    commonData = Separation.separationCommon(reqData);
		    reqBizData = Separation.separationBiz(reqData);
		    
		   
        } catch(Exception e){
        	logger.error("입력데이터 Validation 에러" + e);
        	throw new FrameworkException(ErrorCode.ERROR);
        	
        } finally {
        	logger.error("요청데이터 로깅 : " + reqData);	
        	//Request Data 로깅
        	frameworkController.insertReqLog(reqData);
	  	} 
		
	    try {
	        
	    	//비지니스서비스 호출
	        Object[] bizDataObj = {reqBizData};  
	        resBizObj = joinPoint.proceed(bizDataObj);

	        //서비스 응답 시간 
	       	LocalDateTime now = LocalDateTime.now();
	    	resDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    	
	    	//Response Data 응답 세팅
	        resData = Assembly.assembly(commonData, resBizObj);	

		    logger.info("Aspect 정상응답데이터 " + resData);	
		    
        } catch(FrameworkException e){
        	//Response Data 서비스에러 응답 세팅
        	resData = Assembly.assembly(commonData, e);
			logger.error("Aspect 에러응답데이터 " + resData);	
	    	
        } catch(Exception e) {
        	resData = Assembly.assembly(commonData, e);
			logger.error("Aspect 에러응답데이터 " + resData);	
        }
	    finally {
        	
		    logger.info("응답 로그 적재 시작");
	    	//Response Data 로깅
		    frameworkController.insertResLog(resData, resDateTime);
		    logger.info("응답 로그 적재 끝");
	  	}
	    
	    // Response Data 리턴
	    return resData;
	    
	}
	
}
