package com.yhl.ribbon.service.impl;

import com.yhl.ribbon.service.ComputeService;
import org.springframework.stereotype.Component;

@Component
public class ComputeServiceImpl implements ComputeService {
    @Override
    public String helloService() {
        return "服务器不见鸟，稍后重试";
    }
}
