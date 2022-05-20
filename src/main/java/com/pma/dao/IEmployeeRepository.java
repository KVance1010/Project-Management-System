package com.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pma.dto.EmployeeProject;
import com.pma.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
	
	// using nativeQuery allows you to type the sql query as if you were typing it into the database
	@Query(nativeQuery= true, value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "+
	                                  "FROM employee as e LEFT JOIN project_employee as pe ON pe.employee_id = e.employee_id "+
			                           "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject>employeeProjects();
}
