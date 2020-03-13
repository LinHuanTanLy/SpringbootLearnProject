package com.ly.learn01.controller.user;


import com.ly.learn01.api.CommResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@Api("用户管理")
@RestController
public class UserController {

    @PostMapping("/login")
    public CommResult<String> login(){
        return CommResult.fail("尚未登录");
    }


    @GetMapping("/hello")
    public CommResult<String> user() {
        return CommResult.suc("hello user");
    }

    @GetMapping("/admin")
    public CommResult<String> admin() {
        return CommResult.suc("hello admin");
    }
}
