package com.test.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLoadBalancer {

    ServiceInstance serviceInstance(List<ServiceInstance> instances);
}
