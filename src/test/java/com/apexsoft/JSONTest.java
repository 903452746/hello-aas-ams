package com.apexsoft;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.util.TypeUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JSONTest {
    public static class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Test
    public void testAutoType(){
        Map<String,Object> data= new HashMap<>();
        data.put("aaa",new HashMap<String,String>(){{
            put("@type",User.class.getName());
            put("name","1");
        }});

        String str = JSON.toJSONString(data);
        System.out.println(str);
        TypeUtils.addMapping(User.class.getName(),User.class);
        JSONObject  jsonObject  = JSON.parseObject(str);
        System.out.println(jsonObject.toJSONString());
    }
}
