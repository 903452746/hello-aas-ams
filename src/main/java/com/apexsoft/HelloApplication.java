package com.apexsoft;

import com.apexsoft.extra.model.ARequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.util.HashMap;

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

    @Autowired(required = false)
    private AServiceFegin aServiceFegin;

    public void test(){
        ARequest request = new ARequest();
        request.setParams(new HashMap<String,Object>(){{
            put("1111","22222");
        }});
        aServiceFegin.sendRequest("guoyuan", "test.add", request);
    }
}

