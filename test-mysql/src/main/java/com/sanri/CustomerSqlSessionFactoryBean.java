package com.sanri;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;

import java.io.IOException;

/**
 * 
 * 创建时间:2017-8-27下午2:10:33<br/>
 * 创建者:sanri<br/>
 * 功能:自定义 sqlsessionFactory 拦截 mybatis xml 错误<br/>
 */
public class CustomerSqlSessionFactoryBean extends SqlSessionFactoryBean {
	private Log logger = LogFactory.getLog(getClass());

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		try {  
		    return super.buildSqlSessionFactory();  
		} catch (NestedIOException e) {
			logger.error("解析 xml 出错",e);
			return null;
		} finally {
		    ErrorContext.instance().reset();  
		}
	}
}
