package com.sanri.test.testmvc.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 异常处理，可以绑定多个
     * @return
     */
    @ExceptionHandler(Exception.class)
    public int result(Exception e){
        e.printStackTrace();
        return -1;
    }
}
