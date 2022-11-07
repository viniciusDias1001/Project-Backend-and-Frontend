package io.github.vinicius1001.catalog.controller;

import io.github.vinicius1001.catalog.entities.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findAll() {

        List<Category> lista = new ArrayList<>();
        lista.add(new Category(1L,"Esporte"));
        lista.add(new Category(2L,"Moda"));

        return  lista;
    }
}
