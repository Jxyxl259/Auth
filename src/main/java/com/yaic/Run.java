package com.yaic;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
//mybatis reload xml
@MapperScan(basePackages={"com.yaic.auth.thirdparty.dao","com.yaic.quartz.dao","com.yaic.auth.common.init.dao"})
public class Run{
	
	private static final Logger logger =  LoggerFactory.getLogger(Run.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
		logger.info("服务启动成功.");
	}
}
