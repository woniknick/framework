package com.fwselect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class FwselectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FwselectApplication.class, args);
	}

}
