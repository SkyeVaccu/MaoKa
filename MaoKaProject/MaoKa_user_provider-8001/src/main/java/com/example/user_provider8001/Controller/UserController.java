package com.example.user_provider8001.Controller;

import Entity.User;
import Utils.RandomUtil;
import com.example.user_provider8001.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody  User user){
        return userService.isExistByUser(user);
    }

    @PostMapping("/getUserByAccount")
    public User getUserByAccount(@RequestParam("account") String account,@RequestParam("loginType") int loginType){
        return userService.getUserByAccount(account,loginType);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        //设置一个account
        while(true){
            String account= RandomUtil.getNewId();
            //判断account是否使用过
            if(!userService.isExistByAccount(account,1)) {
                //没有使用过就将数据插入到数据库中
                user.setAccount(account);
                break;
            }
        }
        //注册成功返回账号，注册失败返回null
        return userService.addUser(user)?user.getAccount():"";
    }
}
