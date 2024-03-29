package com.example.concurrency.example.controller;

import com.example.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("threadLocal")
public class ThreadLocalController {

    @GetMapping("test")
    public Long test() {
        return RequestHolder.getId();

    }
}
