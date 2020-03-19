package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MaokaServer6001Application {
    public static void main(String[] args) {
        SpringApplication.run(MaokaServer6001Application.class, args);
    }
}
