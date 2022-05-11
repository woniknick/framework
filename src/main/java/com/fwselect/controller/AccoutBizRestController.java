package com.fwselect.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fwselect.data.SData;
import com.fwselect.service.AccountService;

@RestController
public class AccoutBizRestController {
	private static Logger logger = LoggerFactory.getLogger(AccoutBizRestController.class);
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/getaccount", method = RequestMethod.POST)
	public Object account(@RequestBody Object param){
		
		SData inputData = new SData(param);
		logger.info("요청값  : " + inputData.toString());
		
		SData outputData = accountService.getAccount(inputData);
		logger.info("리턴값  : " + outputData.toString());

		return outputData;
	}
}
