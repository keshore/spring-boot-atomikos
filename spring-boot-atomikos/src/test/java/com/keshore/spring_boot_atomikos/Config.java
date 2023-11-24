//package com.keshore.spring_boot_atomikos;
//
//import javax.jms.ConnectionFactory;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.junit.ClassRule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.utility.DockerImageName;
//
//
//
//@Configuration
//@EnableJms
//public class Config {
//	@ClassRule
//	public static GenericContainer<?> activeMqContainer = new GenericContainer<>(
//			DockerImageName.parse("rmohr/activemq:5.14.3")).withExposedPorts(61616);
//
//	@Bean
//	public JmsListenerContainerFactory<?> jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		return factory;
//	}
//
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		String brokerUrlFormat = "tcp://%s:%d";
//		String brokerUrl = String.format(brokerUrlFormat, activeMqContainer.getHost(),
//				activeMqContainer.getFirstMappedPort());
//		return new ActiveMQConnectionFactory(brokerUrl);
//	}
//
//	@Bean
//	public JmsTemplate jmsTemplate() {
//		return new JmsTemplate(connectionFactory());
//	}
//}
