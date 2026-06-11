package com.hugo.portfolio_api.project.dto.request;

import com.hugo.portfolio_api.project.entity.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

public record CreateProjectRequest(
        @NotBlank(message = "Un nom est nécessaire pour le projet")
        @Size(min = 2, max = 100, message = "Le nom doit faire entre 2 et 100 caractères")
        String name,

        @NotBlank(message = "Une description est nécessaire")
        @Size(min = 10, max = 2500, message = "La description doit faire entre 10 et 2500 caractères")
        String description,

        @NotBlank(message = "Un lien Github est nécessaire")
        @URL(regexp = "^https?://(www\\.)?github\\.com/.*", message = "Lien Github invalide")
        String githubUrl,

        @URL(message = "Lien URL invalide")
        String liveUrl,

        @NotNull(message="Un statut du projet est nécessaire")
        ProjectStatus status,

        Set<Long> technologyIds
) {

}