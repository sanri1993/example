package com.sanri.test.testmybatis.po;

import javax.persistence.*;

import com.sanri.test.testmybatis.enums.BatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * 作  者: 9420
 * 时  间: 2019-09-02 21:03:27
 * 数据表: 127.0.0.1:3306 test batch
 *
 * 测试批量添加
 */
@Entity
@Table(name="batch")
@Data
@ToString
public class Batch implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// 测试日期值
	@Column(name = "date")
	private java.util.Date date;

	// 家庭住址
	@Column(name = "address")
	private String address;

	// 手机号
	@Column(name = "phone")
	private String phone;

	// 测试bool 值
	@Column(name = "success")
	private Boolean success;

	// 身份证
	@Column(name = "idcard")
	private String idcard;

	// 测试字符串
	@Column(name = "name")
	private String name;

	// 职业
	@Column(name = "job")
	private String job;

	// 邮件
	@Column(name = "email")
	private String email;

	// 测试double 值
	@Column(name = "sal")
	private Double sal;

	// 测试枚举值
	@Column(name = "status")
	private BatchStatus status;

	public Batch() {
	}

	public Batch(Date date, String address, String phone, Boolean success, String idcard, String name, String job, String email, Double sal, BatchStatus status) {
		this.date = date;
		this.address = address;
		this.phone = phone;
		this.success = success;
		this.idcard = idcard;
		this.name = name;
		this.job = job;
		this.email = email;
		this.sal = sal;
		this.status = status;
	}
}