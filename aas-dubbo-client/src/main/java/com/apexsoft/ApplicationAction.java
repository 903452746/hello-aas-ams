package com.apexsoft;

import com.alibaba.fastjson.JSONObject;
import com.apexsoft.aas.service.model.ARequest;
import com.apexsoft.extra.AasDubboCommServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ApplicationAction implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger log = LoggerFactory.getLogger(ApplicationAction.class);

    @Autowired
    private AasDubboCommServiceFactory aasDubboCommServiceFactory;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    do {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        doGenicDubbo();
                    } while (++i < 1000);
                }
            }).start();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void doGenicDubbo() {
        ARequest request = new ARequest();
        request.setParams(new HashMap<String, Object>() {{
            put("1111", "22222");
        }});
        try {
            log.info(JSONObject.toJSONString(aasDubboCommServiceFactory.getClient("").sendRequest("guoyuan", "test.add", request)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


}
