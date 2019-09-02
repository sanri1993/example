package com.sanri.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.sanri.pojo.DeviceLogin;

public interface DeviceLoginMapper extends Mapper<DeviceLogin> {

    DeviceLogin selectDeviceLoginBySn(@Param("sn") String sn);

    /**
     * 批量更新数据
     * @param deviceLogins
     * @param timestamp
     */
    void batchUpdate(@Param("deviceLogins") List<DeviceLogin> deviceLogins, @Param("timestamp") long timestamp);

    /**
     * 批量插入数据
     * @param deviceLogins
     */
    void batchInsertDeviceLogin(@Param("deviceLogins") List<DeviceLogin> deviceLogins);

}