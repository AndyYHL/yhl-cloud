package com.yqy.generalplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //@EnableEurekaServer注解启动服务注册中心
public class GeneralplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralplatformApplication.class, args);
    }
}
