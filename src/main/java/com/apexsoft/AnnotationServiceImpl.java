package com.apexsoft;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class AnnotationServiceImpl implements AnnotationService {
    @Override
    public String sayHello(String name) {
        return "annotation: hello, " + name;
    }
}