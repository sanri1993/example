package com.sanri.test.testmybatis.mapper;

import com.sanri.test.testmybatis.po.Batch;
import com.sanri.test.testmybatis.po.EmpDept;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface BatchMapper extends Mapper<Batch>, MySqlMapper<Batch> {

    /**
     * 使用 insert 批量更新
     * @param batchSubList
     */
    void batchUpdateUseInsert(@Param("batchSubList") List<Batch> batchSubList);

    /**
     * 使用临时表更新
     * @param batchSubList
     */
    void batchUpdateUseTmpTable(@Param("batchSubList") List<Batch> batchSubList,@Param("timestamp") long timestamp);
}
