package com.example.login_controller7001.Feign;


import Entity.User;
import com.example.login_controller7001.Hystrix.LoginHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "USERPROVIDER-MICRO",fallbackFactory = LoginHystrix.class)
@Component
public interface LoginFeign{

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public boolean login(@RequestBody User user);
}
