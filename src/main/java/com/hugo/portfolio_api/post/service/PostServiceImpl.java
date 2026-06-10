package com.hugo.portfolio_api.post.service;

import com.hugo.portfolio_api.exception.ResourceNotFoundException;
import com.hugo.portfolio_api.post.dto.request.CreatePostRequest;
import com.hugo.portfolio_api.post.dto.request.UpdatePostRequest;
import com.hugo.portfolio_api.post.dto.response.PostResponse;
import com.hugo.portfolio_api.post.entity.Post;
import com.hugo.portfolio_api.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl
        implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {

        return postRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> findPublished() {

        return postRepository.findByPublished(true)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {

        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + id
                        )
                );

        return mapToResponse(post);

    }

    @Override
    @Transactional(readOnly = true)
    public PostResponse findBySlug(String slug) {

        Post post = postRepository
                .findBySlug(slug)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with slug: "
                                        + slug
                        )
                );

        return mapToResponse(post);

    }

    @Override
    @Transactional
    public PostResponse create(
            CreatePostRequest request
    ) {

        Post post = new Post();

        post.setTitle(request.title());

        post.setSlug(request.slug());

        post.setContent(request.content());

        post.setPublished(false);

        Post savedPost =
                postRepository.save(post);

        return mapToResponse(savedPost);

    }

    @Override
    @Transactional
    public PostResponse update(
            Long id,
            UpdatePostRequest request
    ) {

        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + id
                        )
                );

        post.setTitle(request.title());

        post.setSlug(request.slug());

        post.setContent(request.content());

        Post updatedPost =
                postRepository.save(post);

        return mapToResponse(updatedPost);

    }

    @Override
    @Transactional
    public void delete(Long id) {

        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + id
                        )
                );

        postRepository.delete(post);

    }

    @Override
    @Transactional
    public PostResponse publish(Long id) {

        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + id
                        )
                );

        post.setPublished(true);

        Post updatedPost =
                postRepository.save(post);

        return mapToResponse(updatedPost);

    }

    @Override
    @Transactional
    public PostResponse unpublish(Long id) {

        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + id
                        )
                );

        post.setPublished(false);

        Post updatedPost =
                postRepository.save(post);

        return mapToResponse(updatedPost);

    }

    private PostResponse mapToResponse(
            Post post
    ) {

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getSlug(),
                post.getContent(),
                post.isPublished(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );

    }

}