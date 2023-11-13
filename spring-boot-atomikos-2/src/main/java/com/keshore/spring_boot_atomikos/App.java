package com.keshore.spring_boot_atomikos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("Hello World!");
	}

	// https://github.com/ahmetuygun/xa-demo/blob/main/src/main/java/com/krakozhia/visa/configuration/VisaApplicationDataSourceConfiguration.java

}
