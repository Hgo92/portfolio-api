package com.hugo.portfolio_api.technology.controller;

import com.hugo.portfolio_api.technology.dto.request.CreateTechnologyRequest;
import com.hugo.portfolio_api.technology.dto.request.UpdateTechnologyRequest;
import com.hugo.portfolio_api.technology.dto.response.TechnologyResponse;
import com.hugo.portfolio_api.technology.service.TechnologyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController

@RequestMapping("/api/technologies")
@RequiredArgsConstructor
public class TechnologyController {
    private final TechnologyService technologyService;

    // Ma route GET pour récupérer toutes les technos
    @GetMapping
    public List<TechnologyResponse> findAll() {
        return technologyService.findAll();
    }

    // Ma route GET pour récupérer une techno précise via l'id
    @GetMapping("/{id}")
    public TechnologyResponse findById(
            @PathVariable Long id
    ) {
        return technologyService.findById(id);
    }

    // Ma route POST pour créer une techno
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnologyResponse create(
            @Valid
            @RequestBody
            CreateTechnologyRequest request
    ) {
        return technologyService.create(request);
    }

    // Ma route PUT pour modifier une techno
    @PutMapping("/{id}")
    public TechnologyResponse update(
            @PathVariable Long id,

            @Valid
            @RequestBody
            UpdateTechnologyRequest request
    ) {
        return technologyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        technologyService.delete(id);
    }

    @PostMapping("/{id}/logo")
    public TechnologyResponse uploadLogo(
            @PathVariable
            Long id,
            @RequestParam("file")
            MultipartFile file
    ) {
        return technologyService.uploadLogo(id, file);
    }
}