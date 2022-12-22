package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ProductDTO {
    private Long id;
    private String reference;
    private String title;
    private String description;
    private float initialPrice ;
    private int quantity;
}
