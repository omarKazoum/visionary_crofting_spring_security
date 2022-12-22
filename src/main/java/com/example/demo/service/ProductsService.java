package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.service.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;
    ObjectMapper objectMapper=new ObjectMapper();
    public List<Product> findAll() {
        return productsRepository.findAll();
    }
    public ProductDTO add(ProductDTO productDTO){
        Product p=objectMapper.convertValue(productDTO,Product.class);
        p.setId(null);
        return objectMapper.convertValue(productsRepository.save(p),ProductDTO.class);
    }
    public ProductDTO update(ProductDTO productDTO) throws BusinessException {
        if(productDTO.getId()==null || ! productsRepository.existsById(productDTO.getId()))
            throw new BusinessException("invalid product id");
        Product p=objectMapper.convertValue(productDTO,Product.class);
        return objectMapper.convertValue(productsRepository.save(p),ProductDTO.class);
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }
}
