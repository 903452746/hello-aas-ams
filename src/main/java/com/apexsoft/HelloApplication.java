package com.apexsoft;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created on 2019/1/3.
 *
 * @author Sury
 */
@SpringBootApplication
@EnableFeignClients
@EnableDubbo
@Controller
public class HelloApplication {
    private static final Logger log = LoggerFactory.getLogger(HelloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);

    }


    @RequestMapping("/test")
    @ResponseBody
    public JSONObject test(@RequestBody JSONObject data) {
        log.info(data.toJSONString());
        JSONObject json = new JSONObject();
        json.put("user", "111");
        json.put("test", "user");
        return json;
    }

}

