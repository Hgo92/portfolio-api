package com.hugo.portfolio_api.technology.service;

import com.hugo.portfolio_api.technology.dto.request.CreateTechnologyRequest;
import com.hugo.portfolio_api.technology.dto.request.UpdateTechnologyRequest;
import com.hugo.portfolio_api.technology.dto.response.TechnologyResponse;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface TechnologyService {

     List<TechnologyResponse> findAll();

     TechnologyResponse findById(Long id);

     TechnologyResponse create(
             CreateTechnologyRequest request
     );

     TechnologyResponse update(
             Long id,
             UpdateTechnologyRequest request
     );

     void delete(Long id);

     TechnologyResponse uploadLogo(
             Long id,
             MultipartFile file
     );
}