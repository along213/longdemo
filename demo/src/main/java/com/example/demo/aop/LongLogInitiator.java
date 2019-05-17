package com.example.demo.aop;

import com.example.demo.aop.config.LogRequestProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LogRequestProcessor.class)
public @interface LongLogInitiator {
}
