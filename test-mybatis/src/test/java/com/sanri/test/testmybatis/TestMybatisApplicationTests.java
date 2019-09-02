package com.sanri.test.testmybatis;

import com.sanri.test.testmybatis.enums.BatchStatus;
import com.sanri.test.testmybatis.mapper.BatchMapper;
import com.sanri.test.testmybatis.mapper.EmpMapper;
import com.sanri.test.testmybatis.po.Batch;
import com.sanri.test.testmybatis.po.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatisApplicationTests {

    @Autowired
    EmpMapper empMapper;

    @Autowired
    BatchMapper batchMapper;

    @Test
    public void contextLoads() {
        List<Emp> emps = empMapper.selectAll();
        System.out.println(emps);
    }

    @Test
    public void insert(){
        Batch batch = new Batch();
        batch.setDate(new Date());
        batch.setName("sanri");
        batch.setSuccess(true);
        batch.setSal(22.25);
        batch.setStatus(BatchStatus.PUBLISH);
        batchMapper.insert(batch);
    }

}
