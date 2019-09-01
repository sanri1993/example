package com.sanri.test.testmvc.controller;

import com.sanri.test.testmvc.exception.BusinessException;
import com.sanri.test.testmvc.exception.RemoteException;
import com.sanri.test.testmvc.exception.SystemMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    /**
     * 静态异常展示，固定错误码
     */
    @GetMapping("/staticException")
    public void staticException(){

        throw SystemMessage.ACCESS_DENIED.exception("无权限");
    }

    /**
     * 动态异常，前端不关注错误码
     */
    @GetMapping("/dynamicException")
    public void dynamicException(){

        throw BusinessException.create("名称重复，请使用别的名字");
    }

    /**
     * 第三方调用异常，需显示层级异常
     */
    @GetMapping("/remoteException")
    public void remoteException(){

        //模拟远端错误
        String remoteCode = "E007";
        String remoteMessage = "生效日期必须大于当前日期";

        throw RemoteException.create("某某业务调用错误",remoteCode,remoteMessage);
    }
}
