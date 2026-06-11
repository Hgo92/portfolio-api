package com.hugo.portfolio_api.project.controller;

import com.hugo.portfolio_api.project.dto.request.CreateProjectRequest;
import com.hugo.portfolio_api.project.dto.request.UpdateProjectRequest;
import com.hugo.portfolio_api.project.dto.response.ProjectImageResponse;
import com.hugo.portfolio_api.project.dto.response.ProjectResponse;
import com.hugo.portfolio_api.project.service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponse> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectResponse findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(
            @Valid
            @RequestBody
            CreateProjectRequest request
    ) {
        return projectService.create(request);
    }

    @PutMapping("/{id}")
    public ProjectResponse update(
            @PathVariable Long id,
            @Valid
            @RequestBody UpdateProjectRequest request
    ) {
        return projectService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    @PostMapping("/{id}/images")
    public ProjectImageResponse uploadImages(
            @PathVariable Long id,
            @RequestParam("file")
            MultipartFile file
    ) {
        return projectService.addImage(id, file);
    }

    @DeleteMapping("/{projectId}/images/{imageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(
            @PathVariable Long projectId,
            @PathVariable Long imageId
    ) {
        projectService.deleteImage(projectId, imageId);
    }
}