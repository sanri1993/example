package com.sanri.test.testmybatis.mapper;

import com.sanri.test.testmybatis.po.DeptEmps;
import com.sanri.test.testmybatis.po.Emp;
import com.sanri.test.testmybatis.po.EmpDept;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EmpMapper extends Mapper<Emp> {
    /**
     * 测试日期参数的传递
     * @param birthday
     */
    List<Emp> dateParam(Date birthday);
    /**
     * 一对一查询
     * @param ename
     * @return
     */
    EmpDept selectOne2One(String ename);

    /**
     * 一对多查询
     * @param deptNo
     * @return
     */
    DeptEmps selectOne2Multi(String deptNo);

    /**
     * 分步查询 ，可以设置懒加载；
     * 缺点是需要把这里的方法签名弄到 xml 里面去，对 xml 有很大的侵入性，应该不常用
     * @param deptNo
     * @return
     */
    DeptEmps selectOne2MultiLazy(String deptNo);

    /**
     * 根据部门查询员工信息
     * @param deptNo
     * @return
     */
    List<Emp> selectEmpsByDeptNo(String deptNo);

    /**
     * 使用 emp 中的属性进行查询
     * @param emp
     * @return
     */
    List<Emp> selectEmpsUseParam(Emp emp);

    /**
     * 使用 map 参数进行查询
     * @param map
     * @return
     */
    List<Emp> selectEmpsUseMap(Map<String,Object> map);
}
