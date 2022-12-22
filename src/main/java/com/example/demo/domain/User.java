package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String email;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String password;
    @Enumerated(EnumType.ORDINAL)
    RoleEnum role=RoleEnum.CLIENT;
}
