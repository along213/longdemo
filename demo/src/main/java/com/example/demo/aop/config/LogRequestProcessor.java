package com.example.demo.aop.config;

import com.example.demo.aop.constant.Constant;
import com.example.demo.aop.service.LongLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

public class LogRequestProcessor implements BeanPostProcessor, ApplicationContextAware, BeanClassLoaderAware {

    private ApplicationContext applicationContext;

    private ClassLoader beanClassLoader;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object interceptConfiguration = applicationContext.getBean(Constant.INTERCEPT_CONFIGURATION);

        LongLog annotationsByType = bean.getClass().getAnnotation(LongLog.class);
        if (null!=annotationsByType){
            LongLogHandler handler = new LongLogHandler(interceptConfiguration,bean);
            return Proxy.newProxyInstance(beanClassLoader, bean.getClass().getInterfaces(), handler);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        beanClassLoader = classLoader;
    }
}
