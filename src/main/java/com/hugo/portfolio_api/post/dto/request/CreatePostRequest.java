package com.hugo.portfolio_api.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest (
        @NotBlank
        @Size(max = 255)
        String title,

        @NotBlank
        String content
) {

}