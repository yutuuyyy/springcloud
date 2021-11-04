package com.test.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-04 09:13
 **/
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (null == uname){
            log.info("error!!!!!!!!!!!!!!!!!" + "\t" + "id = " + uname);
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        log.info("success" + "\t" + "id = " + uname);
        log.info("Congratulate!!!!!!!success");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
