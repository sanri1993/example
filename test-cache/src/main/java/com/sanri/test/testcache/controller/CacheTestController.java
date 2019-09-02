package com.sanri.test.testcache.controller;

import com.sanri.test.testcache.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CacheConfig(keyGenerator = "")
public class CacheTestController {
    List<User> users = new ArrayList<>();

    {
        users.add(new User(1,"sanri"));
        users.add(new User(2,"jrj"));
    }

    @GetMapping("/listUser")
    @Cacheable(value = "user",key = "'list'+ targetClass.name + '.'+ methodName + #name ")
    public List<User> listUser(String name){
        return users;
    }

    @GetMapping("/putUser")
    @CachePut(value = "user")
    public int putUser(){
        String name = RandomStringUtils.randomAlphanumeric(5);
        String s = RandomStringUtils.randomNumeric(5);
        int id = NumberUtils.toInt(s);
        users.add(new User(id,name));

        return 0;
    }

    @GetMapping("/clean")
    @CacheEvict(value = "user")
    public int clean(){

        return 0;
    }
}
