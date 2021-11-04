package com.test.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-03 13:30
 **/
@Service
public class PaymentService {

//    ==========服务降级

    public String paymentInfo_OK(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_OK, id: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TIMEOUT(Integer id){
        int timeNumber = 1;
//        int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_TIMEOUT, id: " + id + "耗时： " + timeNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeoutHandler, id: " + id;
    }

//    ===============服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到后熔断
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*********************id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "success, serialNumber:" + "\t" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "error!!!!!!!!!!!!!!!!!" + "\t" + "id:" + "\t" + id;
    }
}
