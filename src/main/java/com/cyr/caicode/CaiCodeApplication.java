package com.cyr.caicode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyr.caicode.mapper")
public class CaiCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaiCodeApplication.class, args);
    }

}
