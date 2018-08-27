package com.techprimers.hystrixdashboardexample;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class RestResource {

    @HystrixCommand(commandKey = "hello1", groupKey = "hello11")
    @GetMapping("/hello1")
    public ResponseEntity<Map<String, String>> hello() {
        Map<String, String> response = Maps.newHashMap();
        //Wrong
        if (RandomUtils.nextBoolean()) {
            response.put("errorCode", "FAILED_400");
            response.put("message", "error!!");
            response.put("status", "400");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @HystrixCommand(commandKey = "hello2", groupKey = "hello22")
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
