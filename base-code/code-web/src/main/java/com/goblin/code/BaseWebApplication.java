package com.goblin.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.goblin.code.dao")
@ServletComponentScan
@EnableScheduling
@EnableAsync
public class BaseWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseWebApplication.class, args);
    }

}
