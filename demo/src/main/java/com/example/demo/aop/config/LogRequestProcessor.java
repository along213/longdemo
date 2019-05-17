package com.example.demo.aop.config;

import com.example.demo.aop.constant.Constant;
import com.example.demo.aop.service.LongLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

public class LogRequestProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object interceptConfiguration = applicationContext.getBean(Constant.INTERCEPT_CONFIGURATION);

        LongLog annotationsByType = bean.getClass().getAnnotation(LongLog.class);
        if (null!=annotationsByType){
            LongLogHandler handler = new LongLogHandler(interceptConfiguration,bean);
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), handler);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
