package com.hugo.portfolio_api.project.dto.response;

import com.hugo.portfolio_api.project.entity.ProjectStatus;
import com.hugo.portfolio_api.technology.dto.response.TechnologyResponse;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectResponse(

        Long id,

        String name,

        String description,

        String githubUrl,

        String liveUrl,

        ProjectStatus status,

        List<ProjectImageResponse> images,

        List<TechnologyResponse> technologies,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {

}