package com.tesr.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-03 15:06
 **/
@Component
public class PaymentFallbackService implements PaymentFeignService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService ------------ paymentInfo_OK-------------fallback";
    }

    @Override
    public String paymentInfo_TIMEOUT(Integer id) {
        return "PaymentFallbackService ------------ paymentInfo_TIMEOUT-------------fallback";
    }
}
