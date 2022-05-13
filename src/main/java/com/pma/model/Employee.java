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
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	// sequence is faster then identity
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;

	@ManyToMany (cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE},
			// fetch allows us to download data from the database to memory
			fetch = FetchType.LAZY)  // lazy vs eager. lazy only downloads the associated children. eager downloads everything
	@JoinTable(name="project_employee", 
    joinColumns = @JoinColumn(name= "employee_id"),          // we need to change this to be the opposite of what is in project
    inverseJoinColumns = @JoinColumn(name="project_id"))     // we need to change this to be the opposite of what is in project
	private List<Project> projects;
	
/*	
	// cascade rule just need to be on the many side
	// cascade allows us to alter the other tables if something has been changed
	// do create a list of rules us {} to enclose everything
	@OneToMany (cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE},
			// fetch allows us to download data from the database to memory
			fetch = FetchType.LAZY)  // lazy vs eager. lazy only downloads the associated children. eager downloads everything
	@JoinColumn(name = "project_id")
	private Project currentProject; // "currentProject" must match what is on the projects relationship
*/
}
