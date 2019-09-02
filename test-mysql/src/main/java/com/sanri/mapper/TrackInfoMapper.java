package com.sanri.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.sanri.pojo.TrackInfo;

public interface TrackInfoMapper extends Mapper<TrackInfo> {

    /**
     * 取设备号为 sn 的最新的一条
     * @param sn
     * @return
     */
    TrackInfo selectBySn(@Param("sn") String sn);

	void updateDeviceTime(@Param("sn")String sn);

	void updateDeviceTimeList(@Param("subTrackInfos")List<TrackInfo> subTrackInfos);

	void updateUserTmpTable(@Param("subTrackInfos")List<TrackInfo> subTrackInfos, @Param("timestamp")long timestamp);

	int insertOne(@Param("trackInfo")TrackInfo trackInfo);

	void updateUserInsert(@Param("trackInfos")List<TrackInfo> trackInfos);

}