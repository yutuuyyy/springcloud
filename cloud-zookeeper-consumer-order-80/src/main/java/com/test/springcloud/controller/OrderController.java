package com.test.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-02 10:39
 **/
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);

        return result;
    }
}
