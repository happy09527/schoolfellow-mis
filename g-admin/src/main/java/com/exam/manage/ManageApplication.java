package com.exam.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: ZhangX
 * @createDate: 2023/4/4
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.exam.manage.mapper")
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class,args);
    }

}
