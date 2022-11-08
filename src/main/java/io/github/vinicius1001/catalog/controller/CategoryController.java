package io.github.vinicius1001.catalog.controller;

import io.github.vinicius1001.catalog.dto.CategoryDTO;
import io.github.vinicius1001.catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryDTO> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                           @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                           @RequestParam(value = "direction", defaultValue = "ASC") String direction) {


        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);


        return categoryService.findAllPaged(pageRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryDTO findById(@PathVariable Long id){
        return    categoryService.findById(id);

    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO save (@RequestBody CategoryDTO category){
        return categoryService.save(category);
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update( @PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        categoryService.delete(id);
    }




}
