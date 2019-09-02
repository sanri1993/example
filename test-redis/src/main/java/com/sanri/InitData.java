package com.sanri;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitData {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@PostConstruct
	public void init(){
		System.out.println(redisTemplate);
	}
}
