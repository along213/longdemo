package com.example.demo.aop.config;

import java.util.HashMap;
import java.util.Map;

public class LongLogUtil {

    private static Map<String,String> handlerMapper = new HashMap<>();

    public static void putMapper(String key,String value){
        handlerMapper.put(key, value);
    }

    public static String getMapper(String key){
        return handlerMapper.get(key);
    }



}
