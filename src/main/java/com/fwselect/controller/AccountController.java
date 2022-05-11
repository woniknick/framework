package com.fwselect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fwselect.service.AccountService;

@Controller
public class AccountController {
    @SuppressWarnings("unused")
	private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    
    @GetMapping("/account")
    public String account(){
    	return "account.html";
    }
}
