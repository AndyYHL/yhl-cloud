package com.yhl.ribbon.service;

import com.yhl.ribbon.service.impl.ComputeServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "compute-service",fallback = ComputeServiceImpl.class)
public interface ComputeService {
    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    String helloService();
}
