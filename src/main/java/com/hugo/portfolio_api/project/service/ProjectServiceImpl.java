package com.hugo.portfolio_api.project.service;

import com.hugo.portfolio_api.exception.ResourceNotFoundException;
import com.hugo.portfolio_api.project.dto.request.CreateProjectRequest;
import com.hugo.portfolio_api.project.dto.request.UpdateProjectRequest;
import com.hugo.portfolio_api.technology.dto.response.TechnologyResponse;

import com.hugo.portfolio_api.project.dto.response.ProjectImageResponse;
import com.hugo.portfolio_api.project.dto.response.ProjectResponse;
import com.hugo.portfolio_api.project.entity.Project;
import com.hugo.portfolio_api.project.entity.ProjectImage;
import com.hugo.portfolio_api.project.entity.ProjectStatus;
import com.hugo.portfolio_api.project.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl
    implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public ProjectResponse findById(Long id) {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Ce projet n'a pas été trouvé")
                );
        return mapToResponse(project);
    }

    @Override
    @Transactional (readOnly = true)
    public List<ProjectResponse> findByStatus(ProjectStatus status) {
        return projectRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public ProjectResponse create(
            CreateProjectRequest request
    ) {
        Project project = new Project();
        project.setName(request.name());
        project.setDescription(request.description());
        project.setGithubUrl(request.githubUrl());
        project.setLiveUrl((request.liveUrl()));
        project.setStatus(request.status());

        Project savedProject = projectRepository.save(project);

        return mapToResponse(savedProject);
    }

    @Override
    @Transactional
    public ProjectResponse update(
            Long id,
            UpdateProjectRequest request
    ) {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ce projet n'a pas été trouvé")
                );

        project.setName(request.name());
        project.setDescription(request.description());
        project.setGithubUrl(request.githubUrl());
        project.setLiveUrl((request.liveUrl()));
        project.setStatus(request.status());

        Project updatedProject = projectRepository.save(project);
        return mapToResponse(updatedProject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ce projet n'a pas été trouvé")
                );
        projectRepository.delete(project);
    }

    @Override
    @Transactional
    public ProjectImageResponse addImage(
            Long projectId,
            MultipartFile file
    ) {
        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ce projet n'a pas été trouvé")
                );

        ProjectImage image = new ProjectImage();
        image.setFilename(file.getOriginalFilename());
        image.setProject(project);
        image.setDisplayOrder(1);
        project.getImages().add(image);

        projectRepository.save(project);

        return new ProjectImageResponse(
                image.getId(),
                image.getFilename(),
                image.getDisplayOrder()
        );
    }

    @Override
    @Transactional
    public void deleteImage(
            Long projectId,
            Long imageId
    ) {
        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ce projet n'a pas été trouvé")
                );

        project.getImages().removeIf(image -> image.getId().equals(imageId));
        projectRepository.save(project);
    }

    private ProjectResponse mapToResponse(
            Project project
    ) {
        return new ProjectResponse(
                        project.getId(),
                        project.getName(),
                        project.getDescription(),
                        project.getGithubUrl(),
                        project.getLiveUrl(),
                        project.getStatus(),
                project.getImages()
                                .stream()
                                .map(image -> new ProjectImageResponse(
                                        image.getId(),
                                        image.getFilename(),
                                        image.getDisplayOrder()
                                ))
                                .toList(),

                project.getTechnologies()
                                .stream()
                                .map(technology -> new TechnologyResponse(
                                        technology.getId(),
                                        technology.getName(),
                                        technology.getLogo()
                                ))
                                .toList(),
                        project.getCreatedAt(),
                        project.getUpdatedAt()
                );


    }
}