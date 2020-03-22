package com.example.login_controller7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.example.login_controller7001.Feign"})
@ComponentScan({"com.example.login_controller7001","MyWebConfiguration"})
public class MaokaLoginController7001Application {

    public static void main(String[] args) {
        SpringApplication.run(MaokaLoginController7001Application.class, args);
    }

}
