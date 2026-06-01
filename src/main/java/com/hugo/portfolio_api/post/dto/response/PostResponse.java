package com.hugo.portfolio_api.post.dto.response;

import java.time.LocalDateTime;

public record PostResponse(

        Long id,

        String title,

        String slug,

        String content,

        Boolean published,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}