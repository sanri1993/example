package com.sanri.pojo;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

@Table(name = "d_track_info")
public class TrackInfo implements Serializable {

    @Id
    @Column(name = "trackerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sn;

    private String imsi;

    private String iccid;

    @Column(name="simPhone")
    private String simPhone;

    @Column(name="isActive")
    private Integer isActive;

    @Column(name="activeDate")
    private Date activeDate;

    @Column(name="checkCode")
    private String checkCode;

    @Column(name="modelId")
    private Integer modelId;

    @Column(name = "d_LoginUserId")
    private Integer loginUserId;

    private Integer issub;

    @Column(name = "vehicleId")
    private Integer vehicleId;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "updateTime")
    private Date updateTime;

    /** 嘀嘀号,以后版本弃用 */
    @Deprecated
    @Column(name = "userId")
    private Integer userId;

    private String imei;

    //软件版本号
    @Column(name = "soft_version")
    private String softVersion;

    //硬件管理id
    @Column(name = "deviceId")
    private Integer deviceId;

    //合作厂商
    private String partner;

    //设备验证状态：0-验证成功；1-正在查询；2-验证失败
    @Column(name = "validateStatus")
    private Integer validateStatus;

    //设备验证次数
    @Column(name = "validateNums")
    private Integer validateNums;

    //网卡号
    @Column(name = "networkCardNubmer")
    private String networkCardNubmer;

    // 1 有源 0无源
    private Integer source;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getNetworkCardNubmer() {
        return networkCardNubmer;
    }

    public void setNetworkCardNubmer(String networkCardNubmer) {
        this.networkCardNubmer = networkCardNubmer;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Integer getValidateStatus() {
        return validateStatus;
    }

    public void setValidateStatus(Integer validateStatus) {
        this.validateStatus = validateStatus;
    }

    public Integer getValidateNums() {
        return validateNums;
    }

    public void setValidateNums(Integer validateNums) {
        this.validateNums = validateNums;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    public String getSimPhone() {
        return simPhone;
    }

    public void setSimPhone(String simPhone) {
        this.simPhone = simPhone == null ? null : simPhone.trim();
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode == null ? null : checkCode.trim();
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }

    public Integer getIssub() {
        return issub;
    }

    public void setIssub(Integer issub) {
        this.issub = issub;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}