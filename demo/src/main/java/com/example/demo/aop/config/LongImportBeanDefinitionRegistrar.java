package com.example.demo.aop.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class LongImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        RootBeanDefinition longLogRequestProcessor = new RootBeanDefinition(LongLogRequestProcessor.class);
        beanDefinitionRegistry.registerBeanDefinition("longLogRequestProcessor", longLogRequestProcessor);
    }
}
