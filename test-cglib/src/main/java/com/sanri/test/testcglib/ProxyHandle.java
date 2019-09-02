package com.sanri.test.testcglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyHandle implements MethodInterceptor {

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        System.out.println(obj);
        System.out.println(method);
        System.out.println(args);
        System.out.println("读 xml 文件 sql ,并执行");
        return proxy;
    }
}
