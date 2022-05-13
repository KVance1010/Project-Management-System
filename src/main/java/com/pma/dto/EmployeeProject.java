package com.pma.dto;

public interface EmployeeProject {

	// need to start with get so spring data knows where to find them
	public String getFirstName();
	public String getLastName();
	public int getProjectCount();
	
}
