package com.example.demo;

import com.example.demo.domain.RoleEnum;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecurityUtils {
    public static Collection<SimpleGrantedAuthority> getAuthoritiesForRole(RoleEnum role){
        List<SimpleGrantedAuthority> authorityList=new ArrayList<>();
        switch (role){
            case CLIENT:
                authorityList.add(new SimpleGrantedAuthority(Authorities.MANAGE_ORDERS))
                ;break;
            case SUPPLIER:
                authorityList.add(new SimpleGrantedAuthority(Authorities.MANAGE_PRODUCTS))
                ;break;
            case ADMIN:
                authorityList.add(new SimpleGrantedAuthority(Authorities.MANAGE_PRODUCTS));
                authorityList.add(new SimpleGrantedAuthority(Authorities.MANAGE_ORDERS))
                ;break;
        }
        return authorityList;

    }
}
