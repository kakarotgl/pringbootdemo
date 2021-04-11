package com.zimug.boot.launch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:/otherconfig/beans.xml"})
//@MapperScan(basePackages = {"com.zimug.boot.launch.mapper"})
public class BootLaunchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootLaunchApplication.class, args);
	}

}
