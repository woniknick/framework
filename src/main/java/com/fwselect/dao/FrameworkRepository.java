package com.fwselect.dao;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.fwselect.data.SData;
import com.fwselect.data.SMultiData;
import com.fwselect.util.FrameworkLog;
import com.fwselect.util.SdataQuery;

@Repository
public class FrameworkRepository {
	private static Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
	
    public void insertLog(SData sData) {
    	logger.info("insertLog 데이터 : " + sData);
    	
    	String sql = "insert into tbl_log values "
    			+ "(:GUID||:REQUESTTYPE, "
    			+ ":GUID, "
    			+ ":PRGNO, "
    			+ ":TRXDATETIME, "
    			+ ":RESDATETIME, "
    			+ "FORMATDATETIME(SYSDATE,'yyyyMMddHHmmss'), "
    			+ ":BIZDATA, "
    			+ ":REQUESTTYPE, "
    			+ ":RESPONSETYPE) ";
    	

    	logger.info("sql : " + sql);
    	
    	FrameworkLog.insertLog(sql, sData);
    }

    public SMultiData getTranLog() {
    	logger.info("getTranLog 시작 ");
    	
    	String sql = "select "
    			+ "logId, "
    			+ "guid, "
    			+ "prgno, "
    			+ "trxDateTime, "
    			+ "resDateTime, "
    			+ "logTime, "
    			+ "bizData, "
    			+ "requestType, "
    			+ "responseType "
    			+ "from tbl_log "
    			+ "order by trxDateTime";
    	
    	SMultiData tranLog = SdataQuery.getMultiData(sql);
    	
    	return tranLog;
    	
    }
    
}
