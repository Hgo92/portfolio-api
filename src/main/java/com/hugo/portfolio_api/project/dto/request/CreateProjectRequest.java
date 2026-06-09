package com.hugo.portfolio_api.project.dto.request;

import com.hugo.portfolio_api.project.entity.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateProjectRequest(
        @NotBlank
        @Size(max = 255)
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