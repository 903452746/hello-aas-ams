package com.apexsoft;


import com.alibaba.fastjson.JSONObject;
import com.apex.ams.util.AasCommService;
import com.apexsoft.aas.service.model.ARequest;

import java.util.HashMap;

public class Client {

    public static void main(String... args) throws InterruptedException {
        while(true){
            Thread.sleep(1000);
            doAms();
        }
    }

    private static void doAms() {
        ARequest request = new ARequest();
        request.setParams(new HashMap<String, Object>() {{
            put("1111", "22222");
        }});
        try {
            System.out.println(JSONObject.toJSONString(AasCommService.sendRequest("guoyuan", "test.add", request)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
