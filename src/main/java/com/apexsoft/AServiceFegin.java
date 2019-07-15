package com.apexsoft;


import com.apexsoft.extra.eureka.AasFeginCommService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("hello-aas-ams")
public interface AServiceFegin extends AasFeginCommService {

}
