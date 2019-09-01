package com.sanri.test.testmvc.exception;

import com.sanri.test.testmvc.dto.ResultEntity;

public interface ExceptionCause<T extends Exception> {
    T exception(Object... args);

    ResultEntity result();
}
