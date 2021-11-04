package com.test.springcloud.lb.impl;

import com.test.springcloud.lb.MyLoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-02 15:38
 **/
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        for (;;) {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
            if (this.atomicInteger.compareAndSet(current, next)){
                System.out.println("***************第几次访问， 次数next: " + next);
                return next;
            }
        }
//        do {
//            current = atomicInteger.get();
//            next = current >= 2147483647 ? 0 : current + 1;
//        }while (!this.atomicInteger.compareAndSet(current, next));
//        System.out.println("***************next: " + next);
//        return next;
    }

    @Override
    public ServiceInstance serviceInstance(List<ServiceInstance> instances) {

        int index = getAndIncrement() % instances.size();

        return instances.get(index);
    }
}
