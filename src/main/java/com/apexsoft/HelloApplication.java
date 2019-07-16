package com.apexsoft;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
 * Created on 2019/1/3.
 *
 * @author Sury
 */
@SpringBootApplication
@EnableFeignClients
@EnableDubbo
public class HelloApplication {


    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);

    }

}

