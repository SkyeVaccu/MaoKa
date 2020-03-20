package com.example.login_controller7001.Controller;

import Entity.User;
import Utils.CookieUtil;
import com.example.login_controller7001.Feign.LoginFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private LoginFeign loginFeign;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public String login(String account, String password, HttpServletResponse response){
        //讲数据分装到对象中
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        //判断是否登录成功
        boolean result = loginFeign.login(user);
        //成功
        if(result){
            //根据信息获得token
            String token = restTemplate.postForObject("www.maoka.com:9001/issueToken", user, String.class);
            //将token添加到Cookie中
            CookieUtil.setCookie("token",token,response);
            return token;
        }
        //失败返回空字符串
        else
            return "";
    }

}
