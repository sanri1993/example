package com.sanri.test.testmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sanri.test.testmybatis.mapper")
public class TestMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMybatisApplication.class, args);
    }

}
