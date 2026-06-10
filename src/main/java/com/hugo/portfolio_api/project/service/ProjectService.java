package com.hugo.portfolio_api.project.service;

import com.hugo.portfolio_api.project.dto.request.CreateProjectRequest;
import com.hugo.portfolio_api.project.dto.request.UpdateProjectRequest;
import com.hugo.portfolio_api.project.dto.response.ProjectImageResponse;
import com.hugo.portfolio_api.project.dto.response.ProjectResponse;
import com.hugo.portfolio_api.project.entity.ProjectStatus;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProjectService {

    List<ProjectResponse> findAll();

    ProjectResponse findById(Long id);

    List<ProjectResponse> findByStatus(
            ProjectStatus status
    );

    ProjectResponse create(
            CreateProjectRequest request
    );

    ProjectResponse update(
            Long id,
            UpdateProjectRequest request
    );

    void delete(Long id);

    ProjectImageResponse addImage(
            Long projectId,
            MultipartFile file
    );

    void deleteImage(
            Long projectId,
            Long imageId
    );
}