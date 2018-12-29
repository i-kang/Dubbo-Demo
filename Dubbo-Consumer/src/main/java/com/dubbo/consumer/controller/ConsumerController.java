package com.dubbo.consumer.controller;

import com.dubbo.consumer.service.ConsumerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 阡陌兮
 * @version 1.0.0
 * @date 2018-12-29 10:52
 * @description
 */
@RestController
public class ConsumerController {

    @Resource
    ConsumerService consumerService;

    @RequestMapping("hello")
    public String hello(){
        return consumerService.hello("Tom");
    }
}
