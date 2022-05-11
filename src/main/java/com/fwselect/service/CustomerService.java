package com.fwselect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fwselect.dao.CustomerRepository;
import com.fwselect.data.SData;

@Transactional
@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    
    //Customer 회원 정보 조회 service
    public SData getCustomer(SData inputdata){
    	return customerRepository.getCustomer(inputdata);
    }
    
}
