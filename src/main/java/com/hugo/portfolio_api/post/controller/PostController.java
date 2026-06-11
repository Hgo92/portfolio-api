package com.hugo.portfolio_api.post.controller;

import com.hugo.portfolio_api.post.dto.request.CreatePostRequest;
import com.hugo.portfolio_api.post.dto.request.UpdatePostRequest;
import com.hugo.portfolio_api.post.dto.response.PostResponse;
import com.hugo.portfolio_api.post.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/posts")
@RequiredArgsConstructor
    public class PostController{
        private final PostService postService;

        @GetMapping
        public List<PostResponse> findAll() {
            return postService.findAll();
        }

        @GetMapping("/published")
        public List<PostResponse> findPublished() {
            return postService.findPublished();
        }

        @GetMapping("/{id}")
        public PostResponse findById(@PathVariable Long id) {
            return postService.findById(id);
        }

        @GetMapping("/{slug}")
        public PostResponse findBySlug(@PathVariable String slug) {
            return postService.findBySlug(slug);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public PostResponse create(
                @Valid
                @RequestBody
                CreatePostRequest request
        ) {
            return postService.create(request);
        }

        @PutMapping("/{id}")
        public PostResponse update(

                @PathVariable
                Long id,

                @Valid

                @RequestBody
                UpdatePostRequest request

        ) {

            return postService.update(
                    id,
                    request
            );

        }

        @DeleteMapping("/{id}")

        @ResponseStatus(HttpStatus.NO_CONTENT)

        public void delete(
                @PathVariable Long id
        ) {

            postService.delete(id);

        }

        @PatchMapping("/{id}/publish")
        public PostResponse publish(
                @PathVariable Long id
        ) {

            return postService.publish(id);

        }

        @PatchMapping("/{id}/unpublish")
        public PostResponse unpublish(
                @PathVariable Long id
        ) {

            return postService.unpublish(id);

        }

    }
