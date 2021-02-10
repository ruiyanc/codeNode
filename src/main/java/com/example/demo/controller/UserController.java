package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {
    @PostMapping("add")
    public Map<String, String> addUser(JSONObject jsonObject) {
        System.out.println(jsonObject.toJSONString());

        return null;
    }
}
