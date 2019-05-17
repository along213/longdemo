package com.example.demo.aop.config;

import com.example.demo.aop.service.RequestLog;
import com.example.demo.aop.service.appoint.Appoint;
import com.example.demo.aop.service.appoint.AppointAfter;
import com.example.demo.aop.service.appoint.AppointBefore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LongLogHandler implements InvocationHandler {

    private Object o;

    private  Object i;

    public LongLogHandler(){}

    public LongLogHandler(Object o,Object i){
        this.o = o;
        this.i = i;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke;
        if(null!=method.getAnnotationsByType(RequestLog.class)){
            for (Object arg : args){
                System.out.println(arg.toString());
            }
        }
        executeBefore(method,args);
        invoke = method.invoke(o, args);
        executeAfter(method,args);
        return invoke;
    }

    private void executeBefore(Method method,Object[] args)throws Throwable{
        AppointBefore[] appoints = method.getAnnotationsByType(AppointBefore.class);
        if(null!=appoints){
            String value = appoints[0].value();
            execute(value,args);
        }
    }

    private void executeAfter(Method method,Object[] args)throws Throwable{
        AppointAfter[] appoints = method.getAnnotationsByType(AppointAfter.class);
        if(null!=appoints) {
            String value = appoints[0].value();
            execute(value,args);
        }
    }

    void execute(String value,Object[] args)throws Throwable{

        int index = value.lastIndexOf(".");
        Class<?> aClass = LongLogHandler.class.getClassLoader().loadClass(value.substring(0, index));
        Method[] methods = aClass.getMethods();
        for (Method m : methods){
            if (m.getName().equals(value.substring(index))){
                if (m.getParameters() == null||m.getParameters().length==0) {
                    m.invoke(i);
                } else {
                    m.invoke(i, args);
                }
                break;
            }
        }
    }

}
