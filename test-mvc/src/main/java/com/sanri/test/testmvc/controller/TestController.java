package com.sanri.test.testmvc.controller;

import com.sanri.test.testmvc.dto.ExampleMessageDto;
import com.sanri.test.testmvc.dto.QueryParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/test")
@Slf4j
@RestController
public class TestController {

    @GetMapping("/exampleMessage")
    public ExampleMessageDto exampleMessageDto(QueryParam queryParam){
        log.info(queryParam.toString());
        int[] ints = {1, 2, 3};
        return new ExampleMessageDto("sanri",9,new Date(),ints);
    }

    @GetMapping("/exampleEmptyReturn")
    public void exampleEmptyReturn(){

    }
}
