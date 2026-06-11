package com.hugo.portfolio_api.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest (
        @NotBlank(message = "Un titre est nécessaire")
        @Size(min=2, max = 255, message="Le titre doit faire en 2 et 255 caractères")
        String title,

        @NotBlank(message="Le post doit avoir un contenu")
        @Size(min=20, max=2500, message="Le post doit faire entre 20 et 5000 caractères")
        String content,

        @NotBlank(message="Un slug est nécessaire")
        String slug
) {

}