package com.apexsoft;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("hello-aas-ams")
public interface AnnotationService {
    @RequestMapping("/sayHello")
    String sayHello(String name);
}
