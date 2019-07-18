package com.apexsoft;


import com.alibaba.fastjson.JSON;
import com.apexsoft.aas.service.annotations.ABusiness;
import com.apexsoft.aas.service.annotations.AService;
import com.apexsoft.aas.service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        response.setRecords(new ArrayList<Map<String,Object>>(){{
           add(new HashMap<String,Object>(){{
               put("aaa",111);
               put("bbb","2222");
           }});
            add(new HashMap<String,Object>(){{
                put("aaa",111);
                put("bbb","2222");
            }});
        }});
        return response;
    }

    //上传请求
    @AService(name = "upload")
    public AUploadResponse upload(AUploadRequest request) {
        UploadFileInfo fileInfo = request.getFileInfo();
        log.info(fileInfo.toString());
        File file = new File(fileInfo.getFileName());
        AUploadResponse response = new AUploadResponse();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = request.getInputStream().read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            response.setCode(1);
            response.setFilecode(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            response.setCode(-1);
            response.setNote(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response.setCode(-1);
            response.setNote(e.getMessage());
        }
        return response;
    }
}
