package com.ly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ly.dao")
public class ManagementSystemOaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementSystemOaApplication.class, args);
    }

}
