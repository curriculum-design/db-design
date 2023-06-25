package com.example.dbdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.dbdesign.mapper")
@SpringBootApplication
public class DbDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbDesignApplication.class, args);
    }
}
