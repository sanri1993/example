package com.sanri.test.testmvc.exception;


import com.sanri.test.testmvc.dto.ResultEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统业务异常(根异常),异常号段为 :
 * 0 : 成功
 * 1 ~ 9999 内定系统异常段
 * 10000 ~ 99999 自定义异常码段
 * 100000 ~ Integer.MAX_VALUE 动态异常码段
 */
public class BusinessException extends RuntimeException {
    protected ResultEntity resultEntity;
    protected static final int  MIN_AUTO_CODE = 100000;

    public static BusinessException create(String message) {
        int value= (int) (MIN_AUTO_CODE + Math.round((Integer.MAX_VALUE - MIN_AUTO_CODE) * Math.random()));
        return create(value + "",message);
    }

    public static BusinessException create(String returnCode,String message){
        if(StringUtils.isBlank(returnCode)){
            return create(message);
        }
         BusinessException businessException = new BusinessException();
         businessException.resultEntity = ResultEntity.err(returnCode).message(message);
         return businessException;
    }

    public static BusinessException create(ExceptionCause exceptionCause ,Object...args){
        ResultEntity resultEntity = exceptionCause.result();
        String message = resultEntity.getMessage();

        if(ArrayUtils.isNotEmpty(args)){
            String [] argsStringArray = new String [args.length];
            for (int i=0;i<args.length;i++) {
                Object arg = args[i];
                argsStringArray[i] = ObjectUtils.toString(arg);
            }
            String formatMessage = String.format(message, argsStringArray);
            resultEntity.setMessage(formatMessage);
        }

        BusinessException businessException = new BusinessException();
        businessException.resultEntity = resultEntity;
        return businessException;
    }

    @Override
    public String getMessage() {
        return resultEntity.getMessage();
    }

    public ResultEntity getResultEntity() {
        return resultEntity;
    }
}
