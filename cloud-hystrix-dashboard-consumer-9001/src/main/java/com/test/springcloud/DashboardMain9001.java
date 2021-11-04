package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-03 15:56
 **/
@SpringBootApplication
@EnableHystrixDashboard
public class DashboardMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(DashboardMain9001.class, args);
    }
}
