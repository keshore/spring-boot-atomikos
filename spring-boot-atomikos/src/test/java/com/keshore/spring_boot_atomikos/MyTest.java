package com.keshore.spring_boot_atomikos;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringRunner.class)
//@SpringBootTest
@TestPropertySource(locations="classpath:application-test.yml")
public class MyTest {

	@ClassRule
//	public static GenericContainer<?> activeMqContainer = new GenericContainer<>(
//			DockerImageName.parse("rmohr/activemq:5.14.3")).withExposedPorts(61616);
	
	public static GenericContainer<?> ibmMqContainer = new GenericContainer<>(
			DockerImageName.parse("icr.io/ibm-messaging/mq:9.3.3.0-r1")).withEnv("license", "accept") .withExposedPorts(1414);
	
	public static GenericContainer<?> postgresContainer = new GenericContainer<>(
			DockerImageName.parse("bitnami/postgresql:16.1.0-debian-11-r1")).withExposedPorts(5432);

	@Test
	public void contextLoads() {
	}
}
