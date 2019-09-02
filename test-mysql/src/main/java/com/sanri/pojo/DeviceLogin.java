package com.sanri.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 设备登陆实体类
 * @author Jay
 * @version 1.0
 * @history 2014-8-19 created by Jay
 */
@Entity
@Table(name="d_device_login")
public class DeviceLogin implements Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="d_device_id")
	private long dDeviceId;
	
	/** 设备sn号 */
	private String sn;
	
	/** 车辆状态  */
	private Integer status;
	
	/** 创建时间  */
	@Column(name="createTime")
	private Date createTime;
	
	/** 最后登录时间*/
	@Column(name="lastLoginTime")
	private Date lastLoginTime;
	
	/** 最后Gprs时间*/
	@Column(name="lastGprsTime")
	private Date lastGprsTime;
	
	/** 最后无效点时间*/
	@Column(name="lastvilidtime")
	private Date lastvilidtime;
	
	/** 最后有效定位时间*/
	@Column(name="lastRtTrack")
	private Date lastRtTrack;
	
	/** 最早有效定位时间*/
	@Column(name="firstHisTrack")
	private Date firstHisTrack;
	
	/** 首次登陆时间*/
	@Column(name="firstLoginTime")
	private Date firstLoginTime;
	
	/** 终端状态*/
	@Column(name="deviceStatus")
	private Integer deviceStatus;
	
	/** 定位状态*/
	@Column(name="locateStatus")
	private Integer locateStatus;
	@Column(name="subSnSwitchStatus")
	private Integer subSnSwitchStatus;
	@Column(name="subSnTime")
	private Date subSnTime;
	
	private Integer speed;

	public long getdDeviceId() {
		return dDeviceId;
	}

	public void setdDeviceId(long dDeviceId) {
		this.dDeviceId = dDeviceId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastGprsTime() {
		return lastGprsTime;
	}

	public void setLastGprsTime(Date lastGprsTime) {
		this.lastGprsTime = lastGprsTime;
	}

	public Date getLastvilidtime() {
		return lastvilidtime;
	}

	public void setLastvilidtime(Date lastvilidtime) {
		this.lastvilidtime = lastvilidtime;
	}

	public Date getLastRtTrack() {
		return lastRtTrack;
	}

	public void setLastRtTrack(Date lastRtTrack) {
		this.lastRtTrack = lastRtTrack;
	}

	public Date getFirstHisTrack() {
		return firstHisTrack;
	}

	public void setFirstHisTrack(Date firstHisTrack) {
		this.firstHisTrack = firstHisTrack;
	}

	public Date getFirstLoginTime() {
		return firstLoginTime;
	}

	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Integer getLocateStatus() {
		return locateStatus;
	}

	public void setLocateStatus(Integer locateStatus) {
		this.locateStatus = locateStatus;
	}

	public Integer getSubSnSwitchStatus() {
		return subSnSwitchStatus;
	}

	public void setSubSnSwitchStatus(Integer subSnSwitchStatus) {
		this.subSnSwitchStatus = subSnSwitchStatus;
	}

	public Date getSubSnTime() {
		return subSnTime;
	}

	public void setSubSnTime(Date subSnTime) {
		this.subSnTime = subSnTime;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
}
