package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//该类为打war包时添加的
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //此处的Application.class为带有@SpringBootApplication注解的启动类
        return application.sources(DemoApplication.class);
    }

}
