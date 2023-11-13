package com.keshore.spring_boot_atomikos.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.atomikos.jms.AtomikosConnectionFactoryBean;

import jakarta.jms.ConnectionFactory;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;

@Configuration
@EnableTransactionManagement
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class})
//@EnableJms
public class AtomikosConfig {

	@Autowired
	ApplicationContext ctx;

	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);
		return userTransactionImp;
	}

	@Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
	public TransactionManager atomikosTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		return userTransactionManager;
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "atomikosTransactionManager" })
	public PlatformTransactionManager transactionManager() throws Throwable {
//		for (String beanName : ctx.getBeanDefinitionNames()) {
//			System.out.println(ctx.getBean("cachingJmsConnectionFactory").getClass());
//			if (beanName.equals("cachingJmsConnectionFactory")) {
//				System.out.println(beanName);
//			}
//		}
		return new JtaTransactionManager(userTransaction(), atomikosTransactionManager());
	}

	@Primary
	@Bean(name = "dataSource")
	@DependsOn("transactionManager")
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.postgres")
	public DataSource datasource() {
		return new AtomikosDataSourceBean();
	}

//	@Primary
//	@Bean
//	@DependsOn("connectionFactory")
//	public JmsTemplate JmsTemplate() {
//		ConnectionFactory k = connectionFactory();
////		com.ibm.mq.jakarta.jms.MQXAConnectionFactory l = (com.ibm.mq.jakarta.jms.MQXAConnectionFactory) k.getXaConnectionFactory();
////		System.out.println(l.sec);
//		UserCredentialsConnectionFactoryAdapter h = new UserCredentialsConnectionFactoryAdapter();
//		h.setTargetConnectionFactory(k);
//		h.setUsername("admin");
//		h.setPassword("password");
//		h.afterPropertiesSet();
//		return new JmsTemplate(h);
//	}
//
//	@Bean(name = "connectionFactory")
//	@DependsOn("transactionManager")
//	@ConfigurationProperties(prefix = "spring.jta.atomikos.connectionfactory.ibmmq")
//	public ConnectionFactory connectionFactory() {
//		return new AtomikosConnectionFactoryBean();
//	}

}
