package com.sanri.test.testmybatis;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用于防止原来的 Mapper 报一个警告
 * No MyBatis mapper was found in '[xxx]' package.
 */
@Mapper
public interface NoWarnMapper{}
