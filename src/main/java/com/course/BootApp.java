package com.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.course.dao.mapper")
public class BootApp {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(BootApp.class);
        springApplication.run(args);

    }
}
