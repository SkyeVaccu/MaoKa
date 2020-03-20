package com.example.user_provider8001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("Dao")
public class MaokaUserProvider8001Application {

    public static void main(String[] args) {
        SpringApplication.run(MaokaUserProvider8001Application.class, args);
    }

}
