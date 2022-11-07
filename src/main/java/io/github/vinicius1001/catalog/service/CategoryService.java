package io.github.vinicius1001.catalog.service;


import io.github.vinicius1001.catalog.repository.CaategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    CaategoryRepository caategoryRepository;

    public CategoryService(CaategoryRepository caategoryRepository) {
        this.caategoryRepository = caategoryRepository;
    }


}
