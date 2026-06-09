package com.hugo.portfolio_api.project.dto.response;

public record ProjectImageResponse(

        Long id,

        String imageUrl,

        Integer displayOrder

) {
}