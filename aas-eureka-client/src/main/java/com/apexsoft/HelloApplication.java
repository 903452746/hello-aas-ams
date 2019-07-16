package com.apexsoft;

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
public class HelloApplication {


    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);

    }

}

