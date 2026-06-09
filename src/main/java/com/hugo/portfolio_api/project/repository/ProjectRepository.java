package com.hugo.portfolio_api.project.repository;

import com.hugo.portfolio_api.project.entity.Project;
import com.hugo.portfolio_api.project.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository
    extends JpaRepository<Project, Long> {
    List<Project> findByStatus(ProjectStatus status);
}