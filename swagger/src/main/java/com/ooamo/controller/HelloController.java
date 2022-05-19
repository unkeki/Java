package com.ooamo.controller;

import com.ooamo.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    其实不止有一个hello请求，还有一个/error，默认的
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

   //只能加在方法上，不嫩注释类
    @ApiOperation("user控制类")
    @GetMapping("/user")
    public User user(){
        return new User();
    }

    @ApiOperation("hello2控制类")
//    @GetMapping("/hello2")
    @PostMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username){
        return "hello"+username;
    }

    @ApiOperation("Post测试类")
    @PostMapping("/post1")
    public User post1(@ApiParam("测试数据") User user){
        return user;
    }
}
