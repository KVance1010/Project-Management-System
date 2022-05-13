package com.pma.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pma.dao.IEmployeeRepository;
import com.pma.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	IEmployeeRepository empRep;
	
	@GetMapping("/new")  
	public String displayEmployeeForm(Model model) {               // model allows you to bind the html with the the object
		Employee anemployee = new Employee();
		model.addAttribute("employee", anemployee);                // "project" must match what was put into the html th:object name
		return "employees/new-employee";                            // thymeleafe allows you to not use the .html
	}

	@PostMapping("/save")
	public String addEmployee(Employee employee, Model model) { 
		empRep.save(employee);
		// use a redirect to prevent duplicate submissions ... this can be redirected any where
		return "redirect:/employees/new";
		// the redirect specifies a url not an html file so we do not have to specify what folder it is in
	}
	
	@GetMapping
	public String displayEmployee(Model model) {
	   List <Employee> employees = empRep.findAll();
	   model.addAttribute("employeeList", employees);
	   return "employees/empList";
	}
	
}
