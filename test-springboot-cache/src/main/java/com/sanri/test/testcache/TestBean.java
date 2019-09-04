package com.sanri.test.testcache;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class TestBean implements InitializingBean, DisposableBean {
    private RedisTemplate redisTemplate;

    public TestBean(RedisTemplate redisTemplate){
        System.out.println("构造注入 redisTemplate");
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct");
    }

    @PreDestroy
    public void preDestory(){
        System.out.println("preDestory");
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        System.out.println("set 注入 redisTemplate ");
        this.redisTemplate = redisTemplate;
    }
}
