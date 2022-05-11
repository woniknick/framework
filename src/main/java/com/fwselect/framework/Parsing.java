package com.fwselect.framework;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fwselect.controller.CustomerBizRestController;
import com.fwselect.data.SData;

public class Parsing {
	
	public static SData parsing(ProceedingJoinPoint joinPoint) {

		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		String[] parameterNames = codeSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();
		
		//SData HashMap에 입력데이터 값 파싱 후 put
		Map<String, Object> params = new HashMap<>();
		
		for (int i = 0; i < parameterNames.length; i++) {
			params.put(parameterNames[i], args[i]);
		}
		SData parsingData = new SData(params.get("param"));
		
		return parsingData;
	}

}
