package com.weien.controller;

import com.pojo.Result;
import com.pojo.User;
import com.weien.service.LoginService;
import com.weien.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (loginService.login(username, password)) {
            String token = JwtUtils.generateToken(user);
           return Result.success(token);
        }else{
           return Result.error("登录失败");
        }
    }


}
