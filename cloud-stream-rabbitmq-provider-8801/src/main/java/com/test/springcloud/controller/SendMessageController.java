package com.test.springcloud.controller;

import com.test.springcloud.service.IMessageProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-08 09:05
 **/
@RestController
public class SendMessageController {

    @Resource
    private IMessageProviderService iMessageProviderService;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return iMessageProviderService.send();
    }
}
