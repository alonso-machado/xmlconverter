package com.alonso.xmlconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@AutoConfiguration
@EntityScan(basePackages = {"com.alonso.xmlconverter.model"})
@SpringBootApplication
public class XmlconverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlconverterApplication.class, args);
	}

}
