package com.example.demo.service;

import com.example.demo.SecurityUtils;
import com.example.demo.domain.*;
import com.example.demo.exceptions.RegisterException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.dto.UserLoginDTO;
import com.example.demo.service.dto.UserRegisterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.stereotype.Service;

import javax.swing.text.Utilities;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authManager;
    @Autowired
    JwtUtil jwtUtil;
    ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isEmpty())
            throw new UsernameNotFoundException("could not find user with email "+email);

        User user=optionalUser.get();

        return new org.springframework.security.core.userdetails.User(email,
                user.getPassword(),
                SecurityUtils.getAuthoritiesForRole(user.getRole()));
    }
    public UserRegisterDTO register(UserRegisterDTO userDTO) throws RegisterException {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent())
            throw new RegisterException("email already in use");

        if (userDTO.getRole().equals(RoleEnum.CLIENT)) {
            //if a client is registering
            Client c = new Client();
            c.setEmail(userDTO.getEmail());
            c.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            c = userRepository.save(c);
            UserRegisterDTO registerDTO = objectMapper.convertValue(c, UserRegisterDTO.class);
            registerDTO.setPassword(null);
            registerDTO.setJwtToken(jwtUtil.generateToken(userDTO.getEmail()));
            return registerDTO;
        }
        if (userDTO.getRole().equals(RoleEnum.SUPPLIER)) {
            //if a supplier is registering
            Supplier s = new Supplier();
            s.setEmail(userDTO.getEmail());
            s.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            s = userRepository.save(s);
            UserRegisterDTO registerDTO = objectMapper.convertValue(s, UserRegisterDTO.class);
            registerDTO.setPassword(null);
            registerDTO.setJwtToken(jwtUtil.generateToken(userDTO.getEmail()));
            return registerDTO;
        }
        if (userDTO.getRole().equals(RoleEnum.ADMIN)) {
            //if a supplier is registering
            Admin admin = new Admin();
            admin.setEmail(userDTO.getEmail());
            admin.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            admin = userRepository.save(admin);
            UserRegisterDTO registerDTO = objectMapper.convertValue(admin, UserRegisterDTO.class);
            registerDTO.setPassword(null);
            registerDTO.setJwtToken(jwtUtil.generateToken(userDTO.getEmail()));
            return registerDTO;
        }
        throw new UsernameNotFoundException("invalid user role");
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public UserLoginDTO login(UserLoginDTO loginDTO) throws AuthenticationCredentialsNotFoundException{
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());
            Class cl=authManager.getClass();
            Class s=authManager.getClass().getSuperclass();
            authManager.authenticate(token);
            UserLoginDTO responseDto=new UserLoginDTO();
            String jwtToken=jwtUtil.generateToken(loginDTO.getEmail());
            responseDto.setJwtToken(jwtToken);
            return responseDto;

    }
}
