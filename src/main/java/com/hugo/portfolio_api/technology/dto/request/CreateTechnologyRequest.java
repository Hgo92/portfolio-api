package com.hugo.portfolio_api.technology.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTechnologyRequest(
        @NotBlank(
                message="Un nom est nécessaire"
        )
        @Size(
                min = 2,
                max = 50,
                message = "Le nom doit faire entre 2 et 50 caractères")
        String name
) {

}