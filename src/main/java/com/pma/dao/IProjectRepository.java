package com.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pma.dto.ChartData;
import com.pma.model.Project;

public interface IProjectRepository extends JpaRepository<Project, Long> {

	@Query(nativeQuery = true, value = "SELECT progress as label, COUNT(*) as value "
			                         + "FROM project "
			                         + "GROUP BY progress")
	public List<ChartData> getProjectStatus();
}
