package com.sanri.test.testcache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanSelf2 implements InitializingBean {
 
    public void add() {
        System.out.println("add");
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("beanSelf2 afterPropertiesSet");
    }
}
