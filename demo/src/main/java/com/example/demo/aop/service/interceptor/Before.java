package com.example.demo.aop.service.interceptor;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 使用该注解方法参数必须和被使用注解方法参数相同或者没有参数
 */
@Target(METHOD)
@Retention(RUNTIME)
@Component
public @interface Before {

    /**
     * 被拦截的范围
     */
    String value() default "";

}
