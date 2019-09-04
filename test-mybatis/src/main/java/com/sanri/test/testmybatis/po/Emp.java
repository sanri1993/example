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

import java.util.Date;
/**
 * 员工信息表
 */
@Entity
@Table(name="emp")
@Data
public class Emp implements Serializable{
	// 员工姓名
	@Column(name="ename",length=10)
	protected String ename;
	// 奖金
	@Column(name="comm",length=0)
	protected Double comm;
	// 上级编号
	@Column(name="mgr",length=0)
	protected Double mgr;
	// 员工编号
	@Column(name="empno",length=0)
	protected Double empNo;
	// 职业
	@Column(name="job",length=9)
	protected String job;
	// 雇佣日期
	@Column(name="hiredate",length=0)
	protected Date hiredate;
	// 部门编号
	@Column(name="deptno",length=0)
	protected String deptNo;
	// 薪水
	@Column(name="sal",length=0)
	protected Double sal;
}
