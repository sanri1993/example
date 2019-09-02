package com.sanri.test.testcglib;

import net.sf.cglib.proxy.Enhancer;

public class CglibProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(UserOperator.class);
        enhancer.setCallback(new ProxyHandle());

        UserOperator userOperator = (UserOperator) enhancer.create();

        userOperator.insertUser(new User("sanri",1));

    }
}
