package com.apexsoft;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("hello-aas-ams")
public interface AnnotationService {
    String sayHello(String name);
}
