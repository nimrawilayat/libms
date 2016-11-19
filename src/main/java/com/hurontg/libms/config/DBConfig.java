package com.hurontg.libms.config;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@Value("${oracle.username}")
	private String username;
	@Value("${oracle.password}")
	private String password;
	@Value("${oracle.url}")
	private String url;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	@Bean
//	public DataSource devDataSource() {
//
//		OracleDataSource dataSource = null;
//		try {
//			dataSource = new OracleDataSource();
//			dataSource.setUser(username);
//			dataSource.setPassword(password);
//			dataSource.setURL(url);
//			dataSource.setImplicitCachingEnabled(true);
//			dataSource.setFastConnectionFailoverEnabled(true);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dataSource;
//	}

	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		PoolProperties p = new PoolProperties();

		p.setUrl("jdbc:sqlserver://localhost:1433;databaseName=libms_db");
		p.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		p.setUsername("libms_user");
		p.setPassword("libms_password");
		p.setJmxEnabled(false);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(10);
		p.setMaxIdle(3);
		p.setMinIdle(3);
		p.setInitialSize(5);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setLogAbandoned(true);
		p.setAbandonWhenPercentageFull(75);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("ConnectionState;StatementFinalizer;ResetAbandonedTimer;SlowQueryReport(threshold=3000)");
		ds.setPoolProperties(p);
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
