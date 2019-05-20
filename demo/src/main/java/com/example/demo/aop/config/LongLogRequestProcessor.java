package com.example.demo.aop.config;

import com.example.demo.aop.constant.Constant;
import com.example.demo.aop.service.LongLog;
import com.example.demo.aop.service.appoint.Appoint;
import com.example.demo.aop.service.appoint.AppointAfter;
import com.example.demo.aop.service.appoint.AppointBefore;
import org.springframework.beans.BeansException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@EnableTransactionManagement
public class LongLogRequestProcessor extends LogRequestProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (null != bean.getClass().getAnnotation(Appoint.class)){
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods){
                AppointAfter appointAfter = method.getAnnotation(AppointAfter.class);
                if (null!= appointAfter)
                    handlerMapper.put("AppointAfter&"+appointAfter.value(), beanName);
                AppointBefore annotation = method.getAnnotation(AppointBefore.class);
                if (null!= annotation)
                    handlerMapper.put("AppointAfter&"+annotation.value(), beanName);
            }
        }
        LongLog annotationsByType = bean.getClass().getAnnotation(LongLog.class);
        if (null!=annotationsByType){
            Object interceptConfiguration = applicationContext.getBean(Constant.INTERCEPT_CONFIGURATION);
            LongLogHandler handler = new LongLogHandler(interceptConfiguration,bean);
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), handler);
        }
        return bean;
    }

}
