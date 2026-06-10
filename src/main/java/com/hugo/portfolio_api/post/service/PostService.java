package com.hugo.portfolio_api.post.service;

import com.hugo.portfolio_api.post.dto.request.CreatePostRequest;
import com.hugo.portfolio_api.post.dto.request.UpdatePostRequest;
import com.hugo.portfolio_api.post.dto.response.PostResponse;

import java.util.List;

public interface PostService {

    List<PostResponse> findPublished();

    List<PostResponse> findAll();

    PostResponse findById(Long id);

    PostResponse findBySlug(String slug);

    PostResponse create(
            CreatePostRequest request
    );

    PostResponse update(
            Long id,
            UpdatePostRequest request
    );

    void delete(Long id);

    PostResponse publish(Long id);

    PostResponse unpublish(Long id);
}