package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-08 14:42
 **/
@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        String result = restTemplate.getForObject(serviceUrl + "/payment/nacos/" + id, String.class);
        return result;
    }
}
