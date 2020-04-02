package com.example.login_controller7001.Hystrix;

import Entity.User;
import com.example.login_controller7001.Feign.LoginFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class LoginHystrix implements FallbackFactory<LoginFeign> {

    @Override
    public LoginFeign create(Throwable throwable) {
        return new LoginFeign() {
            @Override
            public boolean login(User user) {
                return false;
            }

            @Override
            public User getUserByAccount(String account, int loginType) {
                return null;
            }

            @Override
            public String register(User user) {
                return null;
            }
        };
    }
}
