package com.hugo.portfolio_api.technology.repository;

import com.hugo.portfolio_api.technology.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TechnologyRepository
    extends JpaRepository<Technology, Long> {

    Optional<Technology> findByName(String name);

    boolean existsByName(String name);
}