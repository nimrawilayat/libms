package com.hurontg.mars.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.hurontg.mars.persistence")
@PropertySource("/WEB-INF/conf/mars.properties")
public class JpaConfig {

	private static final String DB_DRIVER 				= "driver.class.name";
	private static final String DB_URL 					= "url";	 
  private static final String DB_USERNAME 			= "datasource.username";
  private static final String DB_PASSWORD 			= "datasource.password";
  private static final String DB_PLATFORM 			= "hibernate.jpa.database";
  private static final String DB_DIALECT 			= "hibernate.jpa.dialect";
  private static final String CP_MAX_ACTIVE 		= "max.active";
  private static final String CP_MAX_IDLE 			= "max.idle";
  private static final String CP_MIN_IDLE 			= "min.idle";
  private static final String CP_INITIAL_SIZE	= "initial.size";
  private static final String SHOW_SQL 				= "hibernate.show.sql";
  private static final String GENERATE_DDL 		= "generate.ddl";  
	@Resource
  private Environment env;
	
  @Bean(destroyMethod="close")
  public DataSource dataSource() {
  	org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
    PoolProperties p = new PoolProperties();
    
    p.setUrl(env.getProperty(DB_URL));
    p.setDriverClassName(env.getProperty(DB_DRIVER));
    p.setUsername(env.getProperty(DB_USERNAME));
    p.setPassword(env.getProperty(DB_PASSWORD));
    p.setJmxEnabled(false);
    p.setTestWhileIdle(false);
    p.setTestOnBorrow(true);
    p.setValidationQuery("SELECT 1");
    p.setTestOnReturn(false);
    p.setValidationInterval(30000);
    p.setTimeBetweenEvictionRunsMillis(30000);
    p.setMaxActive(env.getProperty(CP_MAX_ACTIVE, Integer.class));
    p.setMaxIdle(env.getProperty(CP_MAX_IDLE, Integer.class));
    p.setMinIdle(env.getProperty(CP_MIN_IDLE, Integer.class));
    p.setInitialSize(env.getProperty(CP_INITIAL_SIZE, Integer.class));
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
  public JdbcOperations tpl() {
    return new JdbcTemplate(dataSource());
  }
  
  @Bean
  public LocalContainerEntityManagerFactoryBean emf(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    
    emf.setDataSource(dataSource);
    emf.setPersistenceUnitName("marsPU");
    emf.setJpaVendorAdapter(jpaVendorAdapter);
    emf.setPackagesToScan("com.hurontg.mars.domain");    
    
    Properties props = new Properties();
    props.put("hibernate.format_sql", "true");
    props.put("hibernate.use_sql_comments", "true");
    props.put("hibernate.hbm2ddl.auto", "update");
    props.put("hibernate.jdbc.batch_size", "50");		
    
    emf.setJpaProperties(props);
    return emf;
  }
  
  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabase(Database.valueOf(env.getProperty(DB_PLATFORM)));
    adapter.setShowSql(env.getProperty(SHOW_SQL, Boolean.class)==true);
    adapter.setGenerateDdl(env.getProperty(GENERATE_DDL, Boolean.class)==true);
    adapter.setDatabasePlatform(env.getRequiredProperty(DB_DIALECT));
    return adapter;
  }
  

  @Configuration
  @EnableTransactionManagement
  public static class TransactionConfig implements TransactionManagementConfigurer {
    @Inject
    private EntityManagerFactory emf;

    public PlatformTransactionManager annotationDrivenTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(emf);
      return transactionManager;
    }    
  }
  
  @Bean
  public BeanPostProcessor persistenceTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }
  
}
