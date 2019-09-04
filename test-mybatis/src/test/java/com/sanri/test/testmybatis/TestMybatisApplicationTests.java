package com.sanri.test.testmybatis;

import com.sanri.test.testmybatis.enums.BatchStatus;
import com.sanri.test.testmybatis.mapper.BatchMapper;
import com.sanri.test.testmybatis.mapper.EmpMapper;
import com.sanri.test.testmybatis.po.Batch;
import com.sanri.test.testmybatis.po.DeptEmps;
import com.sanri.test.testmybatis.po.Emp;
import com.sanri.test.testmybatis.po.EmpDept;
import com.sanri.test.testmybatis.utils.ListSplitIterator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

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
//        List<Emp> emps = empMapper.selectAll();
//       System.err.println(emps);

        List<Batch> batches = batchMapper.selectAll();
       System.err.println(batches.size());
    }

    /**
     * 测试类型转换器的使用
     */
    @Test
    public void testTypeHandler(){
        Batch batch = new Batch();
        batch.setBirthday(new Date());
        batch.setName("sanri");
        batch.setSuccess(true);
        batch.setSal(22.25);
        batch.setStatus(BatchStatus.PUBLISH);
        batchMapper.insert(batch);
    }

    @Test
    public void testQueryTimeInterceptor(){
        Example example = new Example(Batch.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("phone","176%");
        List<Batch> batches = batchMapper.selectByExample(example);
       System.err.println(batches.size());
    }

    @Test
    public void testOne2One(){
        EmpDept smith = empMapper.selectOne2One("SMITH");
       System.err.println(smith);
    }

    @Test
    public void testOne2Multi(){
        DeptEmps deptEmps = empMapper.selectOne2Multi("20");
       System.err.println(deptEmps);
    }
    
    @Test
    public void testEmpsQuery(){
        List<Emp> emps = empMapper.selectEmpsByDeptNo("20");
       System.err.println(emps);
    }

    @Test
    public void testOne2MultiLazy(){
        DeptEmps deptEmps = empMapper.selectOne2MultiLazy("20");
        System.out.println(deptEmps.getEmps());
        System.out.println();
    }

    @Test
    public void testInsertList(){
        List<Batch> batches = new ArrayList<>();
        String [] batchStatues = {"publish","recall","active"};

        for (int i = 0; i < 10; i++) {
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
            batches.add(new Batch(date,address,phone,success,idcard,name,job,email,sal,batchStatus));
        }
        batchMapper.insertList(batches);
    }

    /**
     * 插入 10 万数据,使用 insertList ，sql 为
     * insert into xxxx values
     * (...),(...)
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

    /**
     * 查看手机号生成效率,平均用时(3 次) : 217 ms
     */
    @Test
    public void test10wPhoneSpendTime(){
        List<Batch> batches = batchMapper.selectAll();
        StopWatch stopWatch = new StopWatch();stopWatch.start();
        for (Batch batch : batches) {
            RandomUtil.phone();
        }
        stopWatch.stop();
        log.info("生成 "+batches.size() + " 手机号用时:"+stopWatch.getTime()+" ms");
    }

    /**
     * 使用 insert into xxx values
     * (...),(...)
     * duplicate key update
     * xx=values('xxxx')
     *来更新数据
     *
     * 实测 100009 数据 ：16G 内存,I5-6500 cpu  3.2G hz
     * 10   条每批，用时：330427 ms
     * 100  条每批，用时：99699 ms 最佳
     * 500  条每批，用时：334591 ms
     * 1000 条每批，用时：629403 ms
     */
    @Test
    public void testBatchUpdateUseInsert(){
        //查询出插入的 10w 数据，然后分批更新，每批 5000 条; 更新手机号为新的手机号
        List<Batch> batches = batchMapper.selectAll();

        log.info("总数量 ："+batches.size()+" 开始更新...");
        StopWatch stopWatch = new StopWatch();stopWatch.start();
        int batchCount = 500;
        ListSplitIterator<Batch> batchListSplitIterator = new ListSplitIterator<>(batches, batchCount);
        while (batchListSplitIterator.hasNext()){
            List<Batch> batchSubList = batchListSplitIterator.next();
            for (Batch batch : batchSubList) {
                batch.setPhone(RandomUtil.phone());
            }
            batchMapper.batchUpdateUseInsert(batchSubList);
            long position = batchListSplitIterator.position();
            log.info("当前进度:"+position+",百分比:"+NumberUtil.percent(position,batches.size(),2)+",耗时:"+stopWatch.getTime()+" ms");
        }
        stopWatch.stop();
        log.info("处理完成，用时:"+stopWatch.getTime() +" ms");
    }

    /**
     * 使用临时表更新
     * 500 条每批 37260 ms
     * 1000 条每批 35180 ms 最佳
     * 1500  条每批 39125 ms
     * 2000 条每批 44772 ms
     */
    @Test
    public void testUpdateUseTmpTable(){
        //查询出插入的 10w 数据，然后分批更新，每批 5000 条; 更新手机号为新的手机号
        List<Batch> batches = batchMapper.selectAll();

        log.info("总数量 ："+batches.size()+" 开始更新...");
        StopWatch stopWatch = new StopWatch();stopWatch.start();
        int batchCount = 1500;
        ListSplitIterator<Batch> batchListSplitIterator = new ListSplitIterator<>(batches, batchCount);
        while (batchListSplitIterator.hasNext()){
            List<Batch> batchSubList = batchListSplitIterator.next();
            for (Batch batch : batchSubList) {
                batch.setPhone(RandomUtil.phone());
            }
            batchMapper.batchUpdateUseTmpTable(batchSubList,System.currentTimeMillis());
            long position = batchListSplitIterator.position();
            log.info("当前进度:"+position+",百分比:"+NumberUtil.percent(position,batches.size(),2)+",耗时:"+stopWatch.getTime()+" ms");
        }
        stopWatch.stop();
        log.info("处理完成，用时:"+stopWatch.getTime() +" ms");
    }
}
