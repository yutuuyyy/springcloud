package com.test.springcloud.service.impl;

import com.test.springcloud.service.IMessageProviderService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-08 08:58
 **/
@EnableBinding(Source.class)
public class IMessageProviderServiceImpl implements IMessageProviderService {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        return null;
    }
}
