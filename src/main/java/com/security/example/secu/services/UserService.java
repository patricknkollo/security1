package com.security.example.secu.services;

import com.security.example.secu.entities.Users;
import com.security.example.secu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<Users> findAllUser(){
        return repository.findAll();
    }
}