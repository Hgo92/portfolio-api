package com.hugo.portfolio_api.technology.service;

import com.hugo.portfolio_api.exception.BusinessException;
import com.hugo.portfolio_api.exception.ResourceNotFoundException;
import com.hugo.portfolio_api.technology.dto.request.CreateTechnologyRequest;
import com.hugo.portfolio_api.technology.dto.request.UpdateTechnologyRequest;
import com.hugo.portfolio_api.technology.dto.response.TechnologyResponse;
import com.hugo.portfolio_api.technology.entity.Technology;
import com.hugo.portfolio_api.technology.repository.TechnologyRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl
    implements TechnologyService {
    private final TechnologyRepository technologyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TechnologyResponse> findAll() {
        List<Technology> technologies = technologyRepository.findAll();

        return technologies.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public TechnologyResponse findById(Long id) {
        Technology technology = technologyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La technologie avec l'id (" + id + ") n'a pas été trouvée"
                        )
                );
    return mapToResponse(technology);
    }

    @Override
    @Transactional
    public TechnologyResponse create(
            CreateTechnologyRequest request
    ) {
        if (technologyRepository.existsByName(
                request.name()
        )) {
            throw new BusinessException(
                    "Cette technologie existe déjà"
            );
        }

        Technology technology = new Technology();
        technology.setName(request.name());
        Technology savedTechnology = technologyRepository.save(technology);

        return mapToResponse(savedTechnology);
    }

    @Override
    @Transactional
    public TechnologyResponse update (
            Long id,
            UpdateTechnologyRequest request
    ) {
        Technology technology = technologyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La technologie avec l'id (" + id + ") n'a pas été trouvée"
                        ));
        technology.setName(request.name());

        Technology updatedTechnology = technologyRepository.save(technology);

        return mapToResponse(updatedTechnology);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Technology technology = technologyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La technologie avec l'id (" + id + ") n'a pas été trouvée"
                        ));

        technologyRepository.delete(technology);
    }

    @Override
    @Transactional
    public TechnologyResponse uploadLogo(
            Long id,
            MultipartFile file
    ) {
        Technology technology = technologyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La technologie avec l'id (" + id + ") n'a pas été trouvée"
                        ));
        technology.setLogo(file.getOriginalFilename());

        Technology updatedTechnology = technologyRepository.save(technology);

        return mapToResponse(updatedTechnology);
    }

    private TechnologyResponse mapToResponse(
            Technology technology
    ) {
        return new TechnologyResponse(
                technology.getId(),
                technology.getName(),
                technology.getLogo()
        );
    }

}