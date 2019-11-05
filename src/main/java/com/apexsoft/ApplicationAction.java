package com.apexsoft;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.apex.ams.client.dynamic.JsonStub;
import com.apex.ams.util.AasCommService;
import com.apexsoft.aas.service.model.ARequest;
import com.apexsoft.aas.service.model.AResponse;
import com.apexsoft.extra.AasDubboCommServiceFactory;
import com.apexsoft.extra.AasFeginCommServiceFactory;
import io.grpc.CallOptions;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ApplicationAction implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger log = LoggerFactory.getLogger(ApplicationAction.class);

    @Autowired
    private AasDubboCommServiceFactory aasDubboCommServiceFactory;

    @Autowired
    private AasFeginCommServiceFactory aasFeginCommServiceFactory;


    @Reference
    private AnnotationService annotationService;


    @Autowired
    private AnnotationService service;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            new Thread(() -> {
                int i = 0;
                do {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //doEureka();
                    //doGenicDubbo();
                    //doDubbo();
                    //doAms();
                    //doJsonAms();
                } while (++i < 1000);
            });

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void doJsonAms() {
        try {
            JsonStub stub = JsonStub.create("guoyuan", "test.add", "handle");
            List<String> requests = new ArrayList<>();
            ARequest request = new ARequest();
            request.setParams(new HashMap<String, Object>() {{
                put("1111", "22222");
            }});


            requests.add(request.toGRPCJsonString());
            stub.call(requests, new StreamObserver<String>() {
                @Override
                public void onNext(String value) {
                    log.info(value);
                    AResponse response = AResponse.buildFromGRPCJson(value);
                    log.info(JSON.toJSONString(response));
                }

                @Override
                public void onError(Throwable t) {
                    log.error("onError:"+t.getMessage(),t);
                }

                @Override
                public void onCompleted() {
                    log.info("onCompleted:");
                }
            },CallOptions.DEFAULT);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    private void doAms() {
        ARequest request = new ARequest();
        request.setParams(new HashMap<String, Object>() {{
            put("1111", "22222");
        }});
        try {
            log.info(JSONObject.toJSONString(AasCommService.sendRequest("guoyuan", "test.add", request)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void doDubbo() {
        log.info(annotationService.sayHello("我是消费者，我在调用"));
    }
    private void doEurke() {
        log.info(service.sayHello("我是消费者，我在调用"));
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


    private void doEureka() {
        ARequest request = new ARequest();
        request.setParams(new HashMap<String, Object>() {{
            put("1111", "22222");
        }});
        try {
            log.info(JSONObject.toJSONString(aasFeginCommServiceFactory.getClient("hello-aas-ams").sendRequest("guoyuan", "test.add", request)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
