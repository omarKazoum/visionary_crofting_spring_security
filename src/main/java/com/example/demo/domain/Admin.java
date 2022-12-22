package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Admin extends User{

    public Admin() {
        setRole(RoleEnum.ADMIN);
    }
}
