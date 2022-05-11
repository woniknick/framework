package com.fwselect.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.fwselect.data.SData;
import com.fwselect.framework.ErrorCode;

public class FrameworkLog {
	private static Logger logger = LoggerFactory.getLogger(FrameworkLog.class);

	static EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	static NamedParameterJdbcTemplate jdbcTemplate =new NamedParameterJdbcTemplate(database);
	
	
	@SuppressWarnings("unchecked")
	public static SData insertLog(String sql, SData input) {    	
		SData result = new SData();
		try {
			jdbcTemplate.update(sql, input);
		}catch(Exception e) {
			logger.error("SQL(insertLog) Error " + e);
			throw new FrameworkException(ErrorCode.ERROR);
		}
    	
        return result;
    }
}
