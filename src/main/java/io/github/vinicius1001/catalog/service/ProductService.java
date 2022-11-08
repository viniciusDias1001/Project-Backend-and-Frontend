package io.github.vinicius1001.catalog.service;


import io.github.vinicius1001.catalog.dto.CategoryDTO;
import io.github.vinicius1001.catalog.dto.ProductDTO;
import io.github.vinicius1001.catalog.entities.Category;
import io.github.vinicius1001.catalog.entities.Product;
import io.github.vinicius1001.catalog.repository.CaategoryRepository;
import io.github.vinicius1001.catalog.repository.ProductRepository;
import io.github.vinicius1001.catalog.service.exception.DataBaseException;
import io.github.vinicius1001.catalog.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository ProductRepository;

    @Autowired
    CaategoryRepository CategoryRepository;

    public ProductService(ProductRepository ProductRepository, CaategoryRepository caategoryRepository)
    {
        this.ProductRepository = ProductRepository;
        this.CategoryRepository = caategoryRepository;
    }


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
      Page<Product> list = ProductRepository.findAll(pageRequest);
      return list.map(Product -> new ProductDTO(Product));


    }

    @Transactional
    public ProductDTO save (ProductDTO ProductDTO){
        Product Product = new Product();
        copyDtoToEntity(ProductDTO, Product);
        ProductRepository.save(Product);
       return new ProductDTO(Product);

    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> Product = ProductRepository.findById(id);
        Product entity = Product.orElseThrow(() -> new EntityNotFoundException("Product Not Found in databe"));
       return new ProductDTO(entity, entity.getCategories());
    }


    @Transactional
    public void update(ProductDTO ProductDTO, Long id) {
       ProductRepository.findById(id).map(Product -> {
          copyDtoToEntity(ProductDTO,Product);
           ProductRepository.save(Product);
           return Product;
       }).orElseThrow(() -> new EntityNotFoundException("Product Not Found in DataBase"));
    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        product.setDate(productDTO.getDate());

        product.getCategories().clear();

        for(CategoryDTO categoryDTO: productDTO.getCategories()){
            Optional<Category>  list = CategoryRepository.findById(categoryDTO.getId()).
                    map(category -> {
                        product.getCategories().add(category);
                        return category;
                    });
        }


    }

    @Transactional
    public void delete(Long id) {
      ProductRepository.findById(id).map(Product -> {
          ProductRepository.delete(Product);
          return Product;

      }).orElseThrow(() -> new DataBaseException("Integrity violation"));
    }

}
