package com.example.demo.rest.controllers;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductsService;
import com.example.demo.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsResource  {
    @Autowired
    ProductsService productsService;
    @GetMapping("/")
    ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productsService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
        ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
            Optional<Product> optionalProduct=productsService.findById(id);
            if(optionalProduct.isPresent()){
                return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

    @PostMapping("/")
    ResponseEntity add(@RequestBody @Valid ProductDTO productDTO){
        try{
            return new ResponseEntity<>(productsService.add(productDTO),HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/")
    ResponseEntity update(@RequestBody @Valid ProductDTO productDTO){
        try{
            return new ResponseEntity<>(productsService.update(productDTO),HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
