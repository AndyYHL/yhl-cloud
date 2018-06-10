package com.yhl.ribbon.service;

import com.yhl.ribbon.service.impl.ProductServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "product-service",fallback = ProductServiceImpl.class)
public interface ProductService {
    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    String helloService();
}
