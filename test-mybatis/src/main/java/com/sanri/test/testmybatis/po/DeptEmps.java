package com.sanri.test.testmybatis.po;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class DeptEmps extends Dept{
    private List<Emp> emps;
}
