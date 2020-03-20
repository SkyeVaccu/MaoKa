package com.example.user_provider8001.Controller;

import Entity.User;
import com.example.user_provider8001.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean login(User user){
        return userService.isExistByUser(user);
    }
}
