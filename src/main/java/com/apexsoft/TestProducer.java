package com.apexsoft;


import com.alibaba.fastjson.JSON;
import com.apexsoft.aas.service.annotations.ABusiness;
import com.apexsoft.aas.service.annotations.AService;
import com.apexsoft.aas.service.model.ARequest;
import com.apexsoft.aas.service.model.AResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@ABusiness(namespace = "guoyuan",pkg ="test")
public class TestProducer{
    private static final Logger log = LoggerFactory.getLogger(TestProducer.class);

    @AService(name = "add")
    public AResponse add(ARequest request){
        log.info(JSON.toJSONString(request));
        AResponse response = new AResponse();
        response.setData(new HashMap<String,Object>(){{
            put("ret","1111");
        }});

        return response;
    }
}
