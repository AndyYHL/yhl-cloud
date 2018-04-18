package com.yhl.ribbon.controller;

import com.yhl.ribbon.service.ComputeService;
import com.yhl.ribbon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonContriller {
    @Autowired
    RestTemplate restTemplate; //注入负载
    @Autowired
    ProductService productService;
    @Autowired
    ComputeService computeService;

    @RequestMapping(value = "/product/hello",method = RequestMethod.GET)
    public String productHello(){
        return productService.helloService();
        //return restTemplate.getForEntity(" http://product-service/hello",String.class).getBody();
    }
    @RequestMapping(value = "/compute/hello",method = RequestMethod.GET)
    public String publisherHello(){
        return computeService.helloService();
        //return restTemplate.getForEntity("http://compute-service/hello",String.class).getBody();
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello Ribbon-service", HttpStatus.OK);
    }
}
