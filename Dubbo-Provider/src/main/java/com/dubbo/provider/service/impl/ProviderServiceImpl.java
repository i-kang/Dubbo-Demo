package com.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.provider.service.ProviderService;

/**
 * @author 阡陌兮
 * @version 1.0.0
 * @date 2018-12-29 10:39
 * @description HelloWorldServiceImpl
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    /**
     * 说hello
     * @param name 名字
     * @return name
     */
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
