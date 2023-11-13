package com.keshore.spring_boot_atomikos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.jms.ConnectionFactory;

@Configuration
@EnableTransactionManagement
@EnableJms
public class AtomikosConfig {

	@Autowired
	ApplicationContext ctx;

	@Bean
	  public JmsTemplate JmsTemplate(ConnectionFactory jmsConnectionFactory) {
	    JmsTemplate jmsTemplate = new JmsTemplate();
	    jmsTemplate.setConnectionFactory(jmsConnectionFactory);
	    jmsTemplate.setSessionTransacted(true);
	    return jmsTemplate;
	  }
	
//	@Bean(name = "userTransaction")
//	public UserTransaction userTransaction() throws Throwable {
//		UserTransactionImp userTransactionImp = new UserTransactionImp();
//		userTransactionImp.setTransactionTimeout(10000);
//		return userTransactionImp;
//	}
//
//	@Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
//	public TransactionManager atomikosTransactionManager() throws Throwable {
//		UserTransactionManager userTransactionManager = new UserTransactionManager();
//		userTransactionManager.setForceShutdown(false);
//		return userTransactionManager;
//	}
//
//	@Bean(name = "transactionManager")
//	@DependsOn({ "userTransaction", "atomikosTransactionManager" })
//	public PlatformTransactionManager transactionManager() throws Throwable {
//		return new JtaTransactionManager(userTransaction(), atomikosTransactionManager());
//	}
//	
//	@Primary
//	@Bean(name = "dataSource")
//	@DependsOn("transactionManager")
////	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.postgres")
//	public DataSource datasource() {
//		return new AtomikosDataSourceBean();
//	}
	
//	@Bean
//	public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
//		final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		factory.setSessionTransacted(true);
//		return factory;
//	}

//	@Bean
//	public String g() {
//		for (String beanName : ctx.getBeanDefinitionNames()) {
////			System.out.println(beanName);
//		}
//		return null;
//	}
}
