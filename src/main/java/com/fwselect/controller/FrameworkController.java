package com.fwselect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fwselect.data.SData;
import com.fwselect.data.SMultiData;
import com.fwselect.framework.ErrorCode;
import com.fwselect.service.FrameworkService;
import com.fwselect.util.FrameworkException;

@Controller
public class FrameworkController {
	private static Logger logger = LoggerFactory.getLogger(FrameworkController.class);
	
	@Autowired
	private FrameworkService frameworkService;
	
    @GetMapping("/framework")
    public String framework(){
    	return "framework.html";
    }
    
	@GetMapping("/tranlog")
    public String tranlog(Model model){		
		SMultiData tranLog = frameworkService.getTranLog();
        model.addAttribute("tranlog", tranLog);
        return "tranloglist.html";
    }	

	@SuppressWarnings("unchecked")
	public void insertReqLog(SData reqData){
		SData reqLogData = new SData(reqData.get("COMMONDATA"));
		
		reqLogData.put("BIZDATA", reqData.get("BIZDATA").toString());
		
		//응답부 데이터 초기화
		reqLogData.put("RESDATETIME", null);
		reqLogData.put("RESPONSETYPE", null);
	   	    
	    try {
	    	frameworkService.insertLog(reqLogData);
	    }catch (Exception e) {
	    	logger.error("insertReqLog 에러" + e);
	    	throw new FrameworkException(ErrorCode.ERROR);
	    }
	        
	}
	
	@SuppressWarnings("unchecked")
	public void insertResLog(SData resData, String resDateTime){
		SData resLogData = new SData(resData.get("COMMONDATA"));
		
		resLogData.put("BIZDATA", resData.get("BIZDATA").toString());
		
		// 서비스 응답 시간 로깅
		resLogData.put("RESDATETIME", resDateTime);
		
		
    	try {
	    	frameworkService.insertLog(resLogData);
	    }catch (Exception e) {
	    	logger.error("insertResLog 에러 " + e);
	    	throw new FrameworkException(ErrorCode.ERROR);
	    }
	        
	}
}
