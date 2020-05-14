package com.gcp.servicesfile;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employees {

	private String status;
	
	List<Data> data;
	

	public Employees() {
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public List<Data> getData() {
		return data;
	}



	public void setData(List<Data> data) {
		this.data = data;
	}
		
	

	@Override
	public String toString() {
		return "Employees{" +
				"status:'" + status + '\'' +','+
				"data:" + data + 
				'}';
	}
}