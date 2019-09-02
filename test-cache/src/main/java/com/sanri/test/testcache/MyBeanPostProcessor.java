package com.sanri.test.testcache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().getName().startsWith("com.sanri.test.testcache")) {
            System.out.println(beanName + "  =  postProcessBeforeInitialization");
            if ("beanSelf2".equals(beanName)) {
                return null;
            }
        }
        return bean;
    }
 
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().getName().startsWith("com.sanri.test.testcache")) {
            System.out.println(beanName + "  =  postProcessAfterInitialization");
        }
        return bean;
    }
}