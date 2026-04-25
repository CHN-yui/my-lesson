package com.mdkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/** @author 俞乐 */
@MapperScan("com.mdkj.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class CourseApp {  
    public static void main(String[] args) {  
        SpringApplication.run(CourseApp.class, args);
    }  
}
