package com.pma.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pma.dao.IEmployeeRepository;
import com.pma.dao.IProjectRepository;
import com.pma.dto.ChartData;
import com.pma.dto.EmployeeProject;
import com.pma.model.Project;

@Controller
public class HomeController {

	@Autowired
	IProjectRepository projRep;
	@Autowired
	IEmployeeRepository empRep;
	
	@RequestMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		//Map<String, Object> map = new HashMap<>();
		
		// querying the database for projects
		List <Project> projects = projRep.findAll();
		model.addAttribute("projectsList", projects);
		
		
		// we will be using this for the pie chart. This is mapping the progress to the count value
		List<ChartData> projectData = projRep.getProjectStatus();
		
		// converts projectData object into a json structure for use in javascripts
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
	    model.addAttribute("projectStatusCnt", jsonString);
		

	    // querying the database for employees
		List<EmployeeProject> employeesProjectCnt = empRep.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		
		return "main/home";
	
	}
	
}
