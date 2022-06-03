package com.pma.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pma.dao.IEmployeeRepository;
import com.pma.dao.IProjectRepository;
import com.pma.model.Employee;
import com.pma.model.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired  // this will create an instance of a this object.
	IProjectRepository projRep;
	
	@Autowired
	IEmployeeRepository empRep;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {           //model allows you to bind the html with the the object
		Project aProject = new Project();
		List<Employee> employees = empRep.findAll();
		model.addAttribute("project", aProject);              // "project" must match what was put into the html th:object name
		model.addAttribute("allEmployees", employees);        //thymeleafe allows you to not use the .html 
		
		return "projects/new-project"; 
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) { //,@RequestParam List<Long> employees){ no longer need this. only for one to many
		projRep.save(project);

		
/* this is used for one to many relationship 
		//employee table needs to be updated and update their foreign key
		Iterable<Employee> chosenEmployees = empRep.findAllById(employees);
		for(Employee emp : chosenEmployees) {
			emp.setCurrentProject(project);
			empRep.save(emp);
		}
*/		
		
		// use a redirect to prevent duplicate submissions ... this can be redirected any where
		return "redirect:/projects/new";
	}
	
	@GetMapping
	public String displayProjects(Model model) {
		List <Project> projects = projRep.findAll();
		model.addAttribute("projectsList", projects);
	   return "projects/proList";
	}
	
}
