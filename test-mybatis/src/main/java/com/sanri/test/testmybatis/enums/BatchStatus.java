package com.sanri.test.testmybatis.enums;

import tk.mybatis.mapper.util.StringUtil;

public enum BatchStatus {
    PUBLISH("publish","发布"),
    RECALL("recall","重试"),
    ACTIVE("active","激活");

    private String code;
    private String msg;

    BatchStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BatchStatus parser(String batchStatus){
        return BatchStatus.valueOf(batchStatus.toUpperCase());
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
