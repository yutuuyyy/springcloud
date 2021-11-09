package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-08 14:24
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String echo(@PathVariable("id") Integer id) {
        return "Hello Nacos Discovery " + id + "\tPort: " + serverPort;
    }
}
