package com.gcp.servicesfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	private String id;
	private String employee_name;
	private String employee_salary;
	private String employee_age;
	private String profile_image;


	public String getEmployee_name() {
		return employee_name;
	}


	public String getEmployee_salary() {
		return employee_salary;
	}


	public String getEmployee_age() {
		return employee_age;
	}


	public String getProfile_image() {
		return profile_image;
	}


	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}


	public void setEmployee_salary(String employee_salary) {
		this.employee_salary = employee_salary;
	}


	public void setEmployee_age(String employee_age) {
		this.employee_age = employee_age;
	}


	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}


	public Data() {
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "{" +
				"\"id\":\""+id+"\","+
				"\"employee_name\":\""+employee_name+"\","+
				"\"employee_salary\":\""+employee_salary+"\","+
				"\"employee_age\":\""+employee_age+"\","+
				"\"profile_image\":\""+profile_image+"\""+
				"}";
	}
}