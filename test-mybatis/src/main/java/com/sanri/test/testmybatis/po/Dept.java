package com.sanri.test.testmybatis.po;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.Table;

import javax.persistence.Id;

import javax.persistence.GenerationType;

import javax.persistence.GeneratedValue;

import javax.persistence.Column;

import lombok.Data;
import lombok.ToString;

/**
 * 部门信息表
 */
@Entity
@Table(name="dept")
@Data
@ToString
public class Dept implements Serializable{
	// 位置
	@Column(name="loc",length=13)
	private String loc;
	// 部门名称
	@Column(name="dname",length=14)
	private String dname;
	// 部门编号
	@Column(name="deptno",length=0)
	private String deptNo;
}
