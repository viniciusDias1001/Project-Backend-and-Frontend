package io.github.vinicius1001.catalog.controller;

import io.github.vinicius1001.catalog.dto.ProductDTO;
import io.github.vinicius1001.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService ProductService;

    public ProductController(ProductService ProductService) {
        this.ProductService = ProductService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDTO> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                           @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                           @RequestParam(value = "direction", defaultValue = "ASC") String direction) {


        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);


        return ProductService.findAllPaged(pageRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductDTO findById(@PathVariable Long id){
        return    ProductService.findById(id);

    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save (@RequestBody ProductDTO Product){
        return ProductService.save(Product);
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update( @PathVariable Long id, @RequestBody ProductDTO ProductDTO){
        ProductService.update(ProductDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        ProductService.delete(id);
    }




}
