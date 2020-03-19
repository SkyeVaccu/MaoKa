package com.example.passport9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MaokaPassport9001Application {

    public static void main(String[] args) {
        SpringApplication.run(MaokaPassport9001Application.class, args);
    }

}
