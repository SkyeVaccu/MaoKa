package com.example.login_controller7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MaokaLoginController7001Application {

    public static void main(String[] args) {
        SpringApplication.run(MaokaLoginController7001Application.class, args);
    }

}
