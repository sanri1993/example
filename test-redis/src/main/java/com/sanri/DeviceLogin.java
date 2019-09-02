package com.sanri;

import java.io.Serializable;
import java.util.Date;

public class DeviceLogin implements Serializable{
	private String sn;
	private Date createTime;
	private Date lastRtTrack;
	private double speed;
	
	public DeviceLogin(){}
	
	public DeviceLogin(String sn, Date createTime, Date lastRtTrack, double speed) {
		super();
		this.sn = sn;
		this.createTime = createTime;
		this.lastRtTrack = lastRtTrack;
		this.speed = speed;
	}



	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastRtTrack() {
		return lastRtTrack;
	}
	public void setLastRtTrack(Date lastRtTrack) {
		this.lastRtTrack = lastRtTrack;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
