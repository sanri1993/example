package com.sanri.test.testmybatis.po;

import lombok.Data;
import lombok.ToString;

/**
 * mybatis 一对一关联查询
 */
@Data
@ToString
public class EmpDept  extends Emp{
    private Dept dept;
}
