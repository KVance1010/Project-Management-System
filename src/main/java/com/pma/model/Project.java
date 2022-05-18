package com.pma.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity  // tells spring that this should be saved in the database as a table
@Table(name = "project") // this is used for naming the table and is not required
public class Project {
	
	

	public Project() {
		super();
	}

	public Project(String name, String progress, String description, List<Employee> employees) {
		super();
		this.name = name;
		this.progress = progress;
		this.description = description;
		this.employees = employees;
	}

	

	public Project(String name, String progress, String description) {
		super();
		this.name = name;
		this.progress = progress;
		this.description = description;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
	private Long projectId;
	
	@Column(name = "project_name", nullable = false)
	private String name;
	
	@Column(name = "progress", nullable = false) //notstarted, completed, inprogress
	private String progress;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany (cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE},
			// fetch allows us to download data from the database to memory
			fetch = FetchType.LAZY)  // lazy vs eager. lazy only downloads the associated children. eager downloads everything
	@JoinTable(name="project_employee", 
	           joinColumns = @JoinColumn(name= "project_id"),
	           inverseJoinColumns = @JoinColumn(name="employee_id"))
	private List<Employee> employees;
	
/*
	// using mappedby allows us not to create a new table 
    // this will be mapped by project id in the employees table
	@OneToMany(mappedBy = "currentProject") // mapped by must match what is on the employees object
	private List<Employee> employees;
*/	
}
