package com.tesr.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tesr.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-03 14:17
 **/
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallback")
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentFeignService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id){
        int age = 10/0;
        return paymentFeignService.paymentInfo_TIMEOUT(id);
    }

    public String paymentInfo_TimeoutHandler(){
        return "80, 系统繁忙，请稍后重试";
    }

    public String paymentGlobalFallback(){
        return "80全局异常处理, 系统繁忙，请稍后重试";
    }
}
