package com.pma;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pma.dao.IProjectRepository;
import com.pma.model.Project;

@SpringBootTest
@RunWith(SpringRunner.class)  // this is used for junit 4
class ProjectManagementApplicationTests {
	
	@Autowired
	IProjectRepository proRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void ifNewProjextSaved_thenSeccess() {
		Project newProject = new Project("New Test Project", "Complete", "Test Description" );
		proRepo.save(newProject);
		
		assertEquals(5, proRepo.findAll().size());
	}

}
