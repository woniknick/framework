package com.fwselect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fwselect.data.SData;
import com.fwselect.service.CustomerService;

@RestController
public class CustomerBizRestController {
	private static Logger logger = LoggerFactory.getLogger(CustomerBizRestController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/getcustomer", method = RequestMethod.POST)
	public Object customer(@RequestBody Object param){
		SData inputData = new SData(param);
		logger.info("요청값  : " + inputData.toString());
		
		SData outputData = customerService.getCustomer(inputData);
		logger.info("리턴값  : " + outputData.toString());

		return outputData;
	}
}
