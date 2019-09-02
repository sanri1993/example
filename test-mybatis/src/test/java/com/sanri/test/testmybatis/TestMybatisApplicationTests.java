package com.sanri.test.testmybatis;

import com.sanri.test.testmybatis.enums.BatchStatus;
import com.sanri.test.testmybatis.mapper.BatchMapper;
import com.sanri.test.testmybatis.mapper.EmpMapper;
import com.sanri.test.testmybatis.po.Batch;
import com.sanri.test.testmybatis.po.Emp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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

    /**
     * 测试类型转换器的使用
     */
    @Test
    public void testTypeHandler(){
        Batch batch = new Batch();
        batch.setDate(new Date());
        batch.setName("sanri");
        batch.setSuccess(true);
        batch.setSal(22.25);
        batch.setStatus(BatchStatus.PUBLISH);
        batchMapper.insert(batch);
    }

    /**
     * 插入 10 万数据
     */
    @Test
    public void testInsert10wData(){
        String [] batchStatues = {"publish","recall","active"};
        List<Batch> batches = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();stopWatch.start();
        for (int i = 1; i <= 100000; i++) {
            if(i % 100 == 0){
                //批量插入，并初始化列表
                batchMapper.insertList(batches);
                //打印进度
                log.info("当前处理完["+i+"]条,进度:"+NumberUtil.percent(i,100000,2) + " %,耗时:"+stopWatch.getTime()+" ms");
                batches = new ArrayList<>();
            }
            String name = RandomUtil.username();
            String job = RandomUtil.job();
            String email = RandomUtil.email(40);
            String phone = RandomUtil.phone();
            String idcard = RandomUtil.idcard();
            String address = RandomUtil.address();
            Date date = RandomUtil.date();
            double sal = RandomUtils.nextDouble(1000,10000);
            boolean success = RandomUtils.nextBoolean();
            BatchStatus batchStatus = BatchStatus.parser(batchStatues[RandomUtils.nextInt(0,3)]);

            Batch batch = new Batch(date,address,phone,success,idcard,name,job,email,sal,batchStatus);
            batches.add(batch);
        }

        stopWatch.stop();
        log.info("总共耗时:"+stopWatch.getTime());
    }

}
