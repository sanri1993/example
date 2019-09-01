package com.sanri.test.testmvc.config;

import com.alibaba.fastjson.JSONObject;
import com.sanri.test.testmvc.dto.ResultEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Executable;
import java.lang.reflect.Type;

/**
 * 可以定义空返回的时候返回正确的信息，如成功信息
 */
@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Executable executable = returnType.getExecutable();
        if(executable.getDeclaringClass() == GlobalExceptionHandler.class){
            //如果是出异常了，直接返回错误处理
            return body;
        }

        AnnotatedType annotatedReturnType = executable.getAnnotatedReturnType();
        Type type = annotatedReturnType.getType();
        if(type == Void.TYPE){
            // 空返回直接返回成功
            return ResultEntity.ok();
        }
        return body;
    }
}
