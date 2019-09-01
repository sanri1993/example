package com.sanri.test.testmvc.config;

import com.sanri.test.testmvc.dto.ResultEntity;
import com.sanri.test.testmvc.exception.BusinessException;
import com.sanri.test.testmvc.exception.RemoteException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${project.package.prefix:com.sanri.test}")
    protected String packagePrefix;

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResultEntity businessException(BusinessException e){
        printLocalStackTrack(e);
        return e.getResultEntity();
    }

    @ExceptionHandler(RemoteException.class)
    public ResultEntity remoteException(RemoteException e){
        ResultEntity parentResult = e.getParent().getResultEntity();
        ResultEntity resultEntity = e.getResultEntity();
        //返回给前端的是业务错误，但是需要在控制台把远程调用异常给打印出来
        log.error(parentResult.getReturnCode()+":"+parentResult.getMessage()
                +" \n -| "+resultEntity.getReturnCode()+":"+resultEntity.getMessage());

        printLocalStackTrack(e);

        //合并两个结果集返回
        ResultEntity merge = ResultEntity.err(parentResult.getReturnCode())
                .message(parentResult.getMessage()+" \n  |- "+resultEntity.getReturnCode()+":"+resultEntity.getMessage());
        return merge;
    }

    /**
     * 打印只涉及到项目类调用的异常堆栈
     * @param e
     */
    private void printLocalStackTrack(BusinessException e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<StackTraceElement> localStackTrack = new ArrayList<>();
        StringBuffer showMessage = new StringBuffer();
        if (ArrayUtils.isNotEmpty(stackTrace)) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                String className = stackTraceElement.getClassName();
                int lineNumber = stackTraceElement.getLineNumber();
                if (className.startsWith(packagePrefix)) {
                    localStackTrack.add(stackTraceElement);
                    showMessage.append(className + "(" + lineNumber + ")\n");
                }
            }
            log.error("业务异常:" + e.getMessage() + "\n" + showMessage);
        } else {
            log.error("业务异常,没有调用栈 " + e.getMessage());
        }
    }

    /**
     * 异常处理，可以绑定多个
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultEntity result(Exception e){
        e.printStackTrace();
        return ResultEntity.err(e.getMessage());
    }
}
