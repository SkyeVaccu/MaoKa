package com.example.passport9001.Controller;

import Entity.User;
import com.example.passport9001.Util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @CrossOrigin
    @PostMapping("/issueToken")
    public String issueToken(@RequestBody User user) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", user.getId());
        param.put("account", user.getAccount());
        param.put("username",user.getUsername());
        param.put("image",user.getImage());
        return JwtUtil.encode(param);
    }

    @CrossOrigin
    @PostMapping("/parseToken")
    public Map<String, Object> parseToken(String token) {
        return JwtUtil.decode(token);
    }
}
