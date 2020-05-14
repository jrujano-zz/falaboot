package com.gcp.servicesfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@SpringBootApplication
public class ServicesfileApplication {
	String linea;
	String employe;
	String employees_Arr[];
	
	
	private static final Logger log = LoggerFactory.getLogger(ServicesfileApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServicesfileApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*
	 * @Bean public CommandLineRunner run(RestTemplate restTemplate) throws
	 * Exception { Employees employee = restTemplate.getForObject(
	 * "http://dummy.restapiexample.com/api/v1/employees", Employees.class);
	 * 
	 * 
	 * File file = File.createTempFile("csv-output-jr", ".csv"); FileWriter output =
	 * new FileWriter(file); CSVWriter write = new CSVWriter(output);
	 * 
	 * String[] header = { "ID", "Name", "Salary", "Age", "Image" };
	 * write.writeNext(header); List<Data> employees = employee.getData(); for (int
	 * i = 0; i < employees.size(); i++) {
	 * 
	 * write.writeNext(new String[]{employees.get(i).getId(),
	 * employees.get(i).getEmployee_name(), employees.get(i).getEmployee_salary(),
	 * employees.get(i).getEmployee_age(),employees.get(i).getProfile_image()});
	 * 
	 * } write.close();
	 * 
	 * 
	 * 
	 * return args -> { log.info(employee.toString()); }; }
	 */

}
