package com.hugo.portfolio_api.project.dto.request;

import com.hugo.portfolio_api.project.entity.ProjectStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UpdateProjectRequest(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        String githubUrl,

        @NotBlank
        String liveUrl,

        @NotNull
        ProjectStatus status,

        Set<Long> technologyIds
) {

}