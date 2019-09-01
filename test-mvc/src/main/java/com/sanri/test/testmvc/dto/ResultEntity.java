package com.sanri.test.testmvc.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 普通消息返回
 * @param <T>
 */
@Data
@ToString
public class ResultEntity<T> implements Serializable {
    private String returnCode = "0";
    private String message;
    private T data;

    public ResultEntity() {
        this.message = "ok";
    }

    public ResultEntity(T data) {
        this();
        this.data = data;
    }

    public static ResultEntity ok() {
        return new ResultEntity();
    }

    public static ResultEntity err(String returnCode) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.returnCode = returnCode;
        resultEntity.message = "fail";
        return resultEntity;
    }

    public static ResultEntity err() {
        return err("-1");
    }

    public ResultEntity message(String msg) {
        this.message = msg;
        return this;
    }

    public ResultEntity data(T data) {
        this.data = data;
        return this;
    }

}