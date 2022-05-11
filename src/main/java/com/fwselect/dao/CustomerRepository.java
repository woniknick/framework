package com.fwselect.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fwselect.data.SData;
import com.fwselect.data.SMultiData;
import com.fwselect.util.SdataQuery;

@Repository
public class CustomerRepository {
	private static Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    @Autowired
    public CustomerRepository(){
    }
    
	//조회 전용
    //회원정보 호출 Cache 사용
    @Transactional(readOnly=true)
    @Cacheable(value = "customerCache", key = "#sData")
    public SData getCustomer(SData sData) {
    		
    	String sql = "select "
    			+ "customerId, "
    			+ "customerName, "
    			+ "phoneNum, "
    			+ "customerAdd "
    			+ "from tbl_customer "
    			+ "where customerId = :CUSTOMERID";
    	
    	logger.info("inputData : " + sData);
    	logger.info("sql : " + sql);
    	SData result = SdataQuery.getSingleData(sql, sData);
    	
        return result;
    }

}
