package com.techprimers.hystrixdashboardexample;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestResource {

    @HystrixCommand(fallbackMethod = "fallBackHello",
            commandKey = "hello1", groupKey = "hello11")
    @GetMapping("/hello1")
    public String hello() {
        //Wrong
        if (RandomUtils.nextBoolean()) {
            throw new RuntimeException("Failed!");
        }
        return "Hello World 1";
    }

    @HystrixCommand(fallbackMethod = "fallBackHello",
            commandKey = "hello2", groupKey = "hello22")
    @GetMapping("/hello2")
    public String hello2() {
        //Wrong
        if (RandomUtils.nextBoolean()) {
            throw new RuntimeException("Failed!");
        }
        return "Hello World 2";
    }

    public String fallBackHello() {
        return "Fall Back Hello initiated";
    }
}
