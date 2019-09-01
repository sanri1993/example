package com.sanri.test.testmvc.exception;


import com.sanri.test.testmvc.dto.ResultEntity;

public enum  SystemMessage implements ExceptionCause<BusinessException> {
    OK(0,"成功"),
    ARGS_NULL(4000,"参数错误,必填参数 [%s]"),
    NOT_LOGIN(4001,"未登录或 session 失效"),
    PERMISSION_DENIED(4002,"没有权限"),
    DATA_PERMISSION_DENIED(4007,"无数据权限"),
    SIGN_ERROR(4003,"签名错误,你的签名串为 [%s]"),
    CHANNEL_NOT_SUPPORT(4004,"非法访问"),
    NETWORK_ERROR(4005,"网络连接错误或磁盘不可读"),
    ACCESS_DENIED(4006,"禁止访问 %s"),
    DATA_TO_LARGE(5001,"数据过大"),
    REPEAT_DATA(5002,"数据重复 %s"),
    NOT_SUPPORT_OPERATOR(4004,"不支持的操作"),
    NOT_SUPPORT_MIME(6001,"不支持的 MIME类型,当前类型为:%s"),
    SYSUSER_LOGINGNAME_ExIST(4006,"用户名已存在请重新输入"),
    POOL_OBJECT_NOT_ENOUGH(5003,"对象池[%s]对象不足"),
    CALL_MODUL_FAIL(5004,"%s 模块调用错误"),
    SERVICE_CALL_FAIL(5005,"后台服务异常")
    ;
    ResultEntity resultEntity = new ResultEntity();

    private SystemMessage(int returnCode,String message){
        resultEntity.setReturnCode(returnCode+"");
        resultEntity.setMessage(message);
    }

    @Override
    public BusinessException exception(Object...args) {
        return BusinessException.create(this,args);
    }

    @Override
    public ResultEntity result() {
        return resultEntity;
    }

    /**
     * 自定义消息的结果返回
     * @param args
     * @return
     */
    public ResultEntity result(Object ... args){
        String message = resultEntity.getMessage();
        resultEntity.setMessage(String.format(message,args));
        return resultEntity;
    }

    public String getReturnCode(){
        return resultEntity.getReturnCode();
    }
}
