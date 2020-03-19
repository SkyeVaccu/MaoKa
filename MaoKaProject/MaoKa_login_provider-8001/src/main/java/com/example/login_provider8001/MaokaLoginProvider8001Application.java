package com.example.login_provider8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MaokaLoginProvider8001Application {

    public static void main(String[] args) {
        SpringApplication.run(MaokaLoginProvider8001Application.class, args);
    }

}
