package com.keshore.spring_boot_atomikos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keshore.spring_boot_atomikos.entities.City;
import com.keshore.spring_boot_atomikos.repositories.CityRepository;

@RestController
public class HelloController {

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	DataSourceProperties dataSourceProperties;

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("/")
	@Transactional
	public String index() {
		City c = new City();
		jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello");
		c = cityRepo.save(c);
		return "Greetings from Spring Boot!. saved city. id: " + c.getId() + dataSourceProperties.getUrl();
	}
	
	@GetMapping("/err")
	@Transactional
	public String err() {
		City c = new City();
		jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello");
		c = cityRepo.save(c);
		System.out.println(1 / 0);
		return "Greetings from Spring Boot!. saved city. id: " + c.getId() + dataSourceProperties.getUrl();
	}

	@GetMapping("recv")
	@Transactional
	public String recv() {
		try {
			return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
		} catch (JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}

}