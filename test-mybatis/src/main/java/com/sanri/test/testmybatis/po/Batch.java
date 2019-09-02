package com.sanri.test.testmybatis.po;

import com.sanri.test.testmybatis.enums.BatchStatus;
import com.sanri.test.testmybatis.po.typehandler.BatchStatusHandler;
import lombok.Data;
import lombok.ToString;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 测试批量添加
 */
@Entity
@Table(name="batch")
@Data
@ToString
public class Batch implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(generator = "JDBC")
//	@KeySql(useGeneratedKeys = true)
	private Integer id;
	// 测试日期值
	@Column(name="date",length=0)
	private Date date;
	// 测试bool 值
	@Column(name="success",length=0)
	private Boolean success;
	// 测试字符串
	@Column(name="name",length=64)
	private String name;
	// 测试double 值
	@Column(name="sal",length=0)
	private Double sal;
	// 测试枚举值
	@Column(name="status",length=1)
	@ColumnType(typeHandler = BatchStatusHandler.class)
	private BatchStatus status;
}
