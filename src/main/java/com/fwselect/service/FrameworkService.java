package com.fwselect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fwselect.dao.FrameworkRepository;
import com.fwselect.data.SData;
import com.fwselect.data.SMultiData;

@Service
public class FrameworkService {
	private final FrameworkRepository frameworkRepository;
    
    @Autowired
    public FrameworkService(FrameworkRepository frameworkRepository){
        this.frameworkRepository = frameworkRepository;
    }

    //거래로그 insert (FW)
    public void insertLog(SData inputData){
        frameworkRepository.insertLog(inputData);
    }

    //거래내역 로그 조회 service (FW)
    public SMultiData getTranLog() {
    	return new SMultiData(frameworkRepository.getTranLog());

    }
}
