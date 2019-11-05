package com.apexsoft;

import feign.Contract;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;


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

    @Bean
    public Contract contract(){
        return new SpringMvcContract();
    }

    @Bean
    public Encoder encoder(ObjectFactory<HttpMessageConverters> messageConverters){
        return new SpringEncoder(messageConverters);
    }

    @Bean
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters){
        return new SpringDecoder(messageConverters);
    }

}

