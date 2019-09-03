package com.sanri.test.testmybatis.mapper;

import com.sanri.test.testmybatis.po.Batch;
import com.sanri.test.testmybatis.po.EmpDept;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BatchMapper extends Mapper<Batch>, MySqlMapper<Batch> {
    /**
     * 一对一查询
     * @param ename
     * @return
     */
    EmpDept selectOne2One(String ename);
}
