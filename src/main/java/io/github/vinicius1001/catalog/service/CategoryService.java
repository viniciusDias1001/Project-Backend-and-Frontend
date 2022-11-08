package io.github.vinicius1001.catalog.service;


import io.github.vinicius1001.catalog.dto.CategoryDTO;
import io.github.vinicius1001.catalog.entities.Category;
import io.github.vinicius1001.catalog.repository.CaategoryRepository;
import io.github.vinicius1001.catalog.service.exception.DataBaseException;
import io.github.vinicius1001.catalog.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CaategoryRepository caategoryRepository;

    public CategoryService(CaategoryRepository caategoryRepository) {
        this.caategoryRepository = caategoryRepository;
    }


    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
      Page<Category> list = caategoryRepository.findAll(pageRequest);
      return list.map(category -> new CategoryDTO(category));


    }

    @Transactional
    public CategoryDTO save (CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        caategoryRepository.save(category);
       return new CategoryDTO(category);

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> category = caategoryRepository.findById(id);
        Category entity = category.orElseThrow(() -> new EntityNotFoundException("Category Not Found in databe"));
       return new CategoryDTO(entity);
    }


    @Transactional
    public void update(CategoryDTO categoryDTO, Long id) {
       caategoryRepository.findById(id).map(category -> {
           category.setName(categoryDTO.getName());
           caategoryRepository.save(category);
           return category;
       }).orElseThrow(() -> new EntityNotFoundException("Category Not Found in DataBase"));
    }

    @Transactional
    public void delete(Long id) {
      caategoryRepository.findById(id).map(category -> {
          caategoryRepository.delete(category);
          return category;

      }).orElseThrow(() -> new DataBaseException("Integrity violation"));
    }

}
