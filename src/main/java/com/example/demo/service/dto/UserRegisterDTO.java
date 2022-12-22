package com.example.demo.service.dto;

import com.example.demo.domain.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRegisterDTO {
    Long id;
    String email;
    String jwtToken;
    String password;
    RoleEnum role;
}
