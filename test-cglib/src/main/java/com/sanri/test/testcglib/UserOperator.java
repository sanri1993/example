package com.sanri.test.testcglib;

public interface UserOperator {
    User queryByName(String name);
    void insertUser(User user);
}
