package com.fwselect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fwselect.data.SData;
import com.fwselect.data.SMultiData;
import com.fwselect.service.CustomerService;

@Controller
public class CustomerController {
    @SuppressWarnings("unused")
	private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    
    @GetMapping("/customer")
    public String customer(){

    	return "customer.html";
    }
    
    
}
