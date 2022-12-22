package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Client extends User{
    @OneToMany
    private List<Order> orders;

    public Client() {
        setRole(RoleEnum.CLIENT);
    }
}
