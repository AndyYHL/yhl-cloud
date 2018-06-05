package com.yhl.ribbon.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yhl.ribbon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "productServiceFallback")
    public String helloService(){
        return restTemplate.getForEntity(" product-service/hello",String.class).getBody();
    }

    public String productServiceFallback(){
        return "product已沽清";
    }
}
