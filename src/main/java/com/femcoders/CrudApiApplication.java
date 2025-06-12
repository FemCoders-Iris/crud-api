package com.femcoders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApiApplication.class, args);
		System.out.println("Hello world");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC driver is present in the classpath.");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC driver NOT found in the classpath.");
		}
	}
}
