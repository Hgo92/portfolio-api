package com.hugo.portfolio_api.technology.repository;

import com.hugo.portfolio_api.technology.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository
    extends JpaRepository<Technology, Long> {

}