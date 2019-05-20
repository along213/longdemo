package com.example.demo.aop;

import com.example.demo.aop.config.LogRequestProcessor;
import com.example.demo.aop.config.LongImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LongImportBeanDefinitionRegistrar.class)
public @interface LongLogInitiator {
}
