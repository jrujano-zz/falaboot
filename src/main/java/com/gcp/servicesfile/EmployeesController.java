package com.gcp.servicesfile;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EmployeesController {
	String message;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ServicesfileApplication.PubsubOutboundGateway messagingGateway;
	
	private static final Log LOGGER = LogFactory.getLog(EmployeesController.class);

	@RequestMapping("/")
	public String index() {
		return "Test-Johan Rujano";
	}

	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	private RedirectView getEmployees(RedirectAttributes attributes) throws IOException
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
		LOGGER.info("Obteniendo employees");
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
		LOGGER.info("Generando archivos");
		write.close();
		
		attributes.addAttribute("message", "Archivo generados");
        return new RedirectView("postMessage");
		

	}

	
	@RequestMapping("/postMessage")
    public RedirectView publishMessage(@RequestParam("message") String message) {
        messagingGateway.sendToPubsub(message);
        return new RedirectView("/");
    }



}
