package com.fwselect.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.fwselect.data.SData;
import com.fwselect.data.SMultiData;
import com.fwselect.framework.ErrorCode;

public class SdataQuery {
	private static Logger logger = LoggerFactory.getLogger(SdataQuery.class);

	static EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	static NamedParameterJdbcTemplate jdbcTemplate =new NamedParameterJdbcTemplate(database);
	 
	// 단건(SData) 데이터 조회
	public static SData getSingleData(String sql, SData sdata) {

		SData result = new SData();
		try {
	    	Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, sdata);
	    	
	    	for (String key : resultMap.keySet()) {
	    		String value = (String) resultMap.get(key);
	    		result.put(key, value);
	    	}
			
		}catch (IncorrectResultSizeDataAccessException e) {
			logger.error("Biz 데이터 없음 Error" + e);
			throw new FrameworkException(ErrorCode.NODATA);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("SQL(getSingleData) Error" + e);
			throw new FrameworkException(ErrorCode.ERROR);
		}
		return result;
		
	}
	
	// 다건(SMultiData) 데이터 조회
	public static SMultiData getMultiData(String sql) { 

		SData sData = new SData();
		SMultiData result = new SMultiData();	
		try {
			List<Map<String, Object>> resultMap = jdbcTemplate.queryForList(sql, sData);
	    	
	    	for (Map<String, Object> row : resultMap) {
	    		SData sDataMap = new SData();

	    		for (String key : row.keySet()) {
	        		String value = (String) row.get(key);
	        		sDataMap.put(key, value);
	        	}    
	        	
	    		result.add(sDataMap);
	        }
		}catch (IncorrectResultSizeDataAccessException e) {
			logger.error("Biz 데이터 없음 Error" + e);
			throw new FrameworkException(ErrorCode.NODATA);
		}catch(Exception e) {
			logger.error("SQL(getMultiData) Error" + e);
			throw new FrameworkException(ErrorCode.ERROR);
		}
    	
        return result;
    }
	
}