package com.apexsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created on 2019/1/3.
 *
 * @author Sury
 */
@SpringBootApplication
@ServletComponentScan
public class HelloApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HelloApplication.class);
    }
}
