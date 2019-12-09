package com.apexsoft;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.fastjson.JSONObject;
import com.apexsoft.aas.config.ContextEventListener;
import org.apache.catalina.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.web.http.SessionEventHttpSessionListenerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;


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

    @RequestMapping("/test1")
    @ResponseBody
    public JSONObject test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        //session.invalidate();
        JSONObject json = new JSONObject();
        json.put("user", "111");
        json.put("test", "user");
        return json;
    }
    @RequestMapping("/test2")
    @ResponseBody
    public JSONObject test2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        session.invalidate();
        JSONObject json = new JSONObject();
        json.put("user", "111");
        json.put("test", "user");
        return json;
    }
    @Bean
    public SessionHandler sessionHandler() {
        return new SessionHandler();
    }



    public static class SessionHandler implements HttpSessionListener {

        @Override
        public void sessionCreated(HttpSessionEvent se) {
            System.out.println("sessionCreated");
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent se) {
            System.out.println("sessionDestroyed");
        }
    }

    @Component
    public static class ContextEventListener implements ApplicationListener {
        private static final Logger LOG = LoggerFactory.getLogger(com.apexsoft.aas.config.ContextEventListener.class);

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            log.error("====>"+event.toString());
        }
    }

}

