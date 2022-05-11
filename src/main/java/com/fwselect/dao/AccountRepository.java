package com.fwselect.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fwselect.data.SData;
import com.fwselect.util.SdataQuery;


@Repository
public class AccountRepository {
	private static Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

	@Autowired
	public AccountRepository() {
	}
	
	//조회 전용
	@Transactional(readOnly=true) //조회 전용
    public SData getAccount(SData sData) {
		
    	String sql = "select "
    			+ "accountID, "
    			+ "customerId, "
    			+ "openDate, "
    			+ "balance "
    			+ "from tbl_account "
    			+ "where accountID = :ACCOUNTID";
    	logger.info("inputData : " + sData);
    	logger.info("sql : " + sql);
    	SData result = SdataQuery.getSingleData(sql, sData);
    	
        return result;
    }
    
	
}
