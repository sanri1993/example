package com.sanri.test.testcache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class MyBeanFactory implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Iterator<String> iterator = configurableListableBeanFactory.getBeanNamesIterator();
        BeanSelf2 beanSelf = (BeanSelf2) configurableListableBeanFactory.getBean("beanSelf2");
        beanSelf.add();
        System.out.println("beanFactoryPostProcessor");
    }
}
