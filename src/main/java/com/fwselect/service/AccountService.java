package com.fwselect.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fwselect.dao.AccountRepository;
import com.fwselect.data.SData;

@Transactional
@Service
public class AccountService {
	private final AccountRepository accountRepository;
	
	   @Autowired
	    public AccountService(AccountRepository accountRepository){
	        this.accountRepository = accountRepository;
	    }
	   
	   //Account 계좌 정보 조회 service
	    public SData getAccount(SData inputdata){
	    	return accountRepository.getAccount(inputdata);
	    }
	    
	

}
