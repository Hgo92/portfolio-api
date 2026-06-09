package com.hugo.portfolio_api.post.repository;

import com.hugo.portfolio_api.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository
    extends JpaRepository<Post, Long> {
    boolean existsBySlug(String slug);

    List<Post> findByPublished(boolean published);
}