package com.example.login_controller7001.AddtionalBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LoginRibbon {
    @Bean
    public RestTemplate getRibbon(){
        return new RestTemplate();
    }
}
