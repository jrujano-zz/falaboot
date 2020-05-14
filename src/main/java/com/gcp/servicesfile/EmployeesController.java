package com.gcp.servicesfile;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class EmployeesController {
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/")
	public String index() {
		return "Test-Johan Rujano";
	}
	
	@RequestMapping("/employees")
	private static void getEmployees() throws IOException
	{
	    final String uri = "http://dummy.restapiexample.com/api/v1/employees";
	    RestTemplate restTemplate = new RestTemplate();
	     
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 * HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		 * 
		 * ResponseEntity<Employees> result = restTemplate.exchange(uri, HttpMethod.GET,
		 * entity, Employees.class);
		 * 
		 * System.out.println(result);
		 */
		Employees employee = restTemplate.getForObject(uri,  Employees.class);
		
		File file = File.createTempFile("fala-csv-output-", ".csv");
		FileWriter output = new FileWriter(file);
		CSVWriter write = new CSVWriter(output);
	    	
		String[] header = { "ID", "Name", "Salary", "Age", "Image" };
        write.writeNext(header);
		List<Data> employees = employee.getData();
		for (int i = 0; i < employees.size(); i++) {
					
			write.writeNext(new String[]{employees.get(i).getId(), employees.get(i).getEmployee_name(), employees.get(i).getEmployee_salary(), employees.get(i).getEmployee_age(),employees.get(i).getProfile_image()});
		    
		}
		write.close();
		
	}
	

}
