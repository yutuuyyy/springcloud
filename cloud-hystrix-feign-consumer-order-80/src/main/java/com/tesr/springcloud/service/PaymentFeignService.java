package com.tesr.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-hystrix-provider-payment", fallback = PaymentFallbackService.class)
public interface PaymentFeignService {

    @GetMapping(value = "payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "payment/hystrix/timeout/{id}")
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id);
}
