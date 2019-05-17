package com.example.demo.aop.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LongApplicationContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private LongApplicationContext() {
    }

    public static Map<String, Object> getService(Class t){
        Map<String, Object> beans = applicationContext.getBeansOfType(t);
        return beans;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(String className){
        return applicationContext.getBean(className);
    }
}
