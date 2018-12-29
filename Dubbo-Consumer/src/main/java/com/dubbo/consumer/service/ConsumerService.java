package com.dubbo.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.provider.service.ProviderService;
import org.springframework.stereotype.Service;

/**
 * @author 阡陌兮
 * @version 1.0.0
 * @date 2018-12-29 10:47
 * @description ConsumerService
 */
@Service
public class ConsumerService {

    @Reference
    private ProviderService providerService;

    public String hello(String name){
        return providerService.sayHello(name);
    }
}
