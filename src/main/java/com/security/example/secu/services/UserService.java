package com.security.example.secu.services;

import com.security.example.secu.entities.Users;
import com.security.example.secu.exceptions.NoUserFoundException;
import com.security.example.secu.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    public List<Users> findAllUser(){
        return repository.findAll();
    }

    public Users findUserWithID(Long userid) throws NoUserFoundException {
        Optional<Users>optionalUsers= repository.findById(userid);
        if(optionalUsers.isPresent()){
            logger.info("user found,  username: {}", optionalUsers.get().getUsername());
            return optionalUsers.get();
        }
        else {
            throw new NoUserFoundException("user with id "+ userid+ "doesn't exist in the database");
        }
    }

    public Users findUserWithEmail(String email) throws NoUserFoundException {
        Optional<Users>optionalUsers= repository.findUserByEmailAdresse(email);
        if(optionalUsers.isPresent()){
            logger.info("user found,  username: {}", optionalUsers.get().getUsername());
            return optionalUsers.get();
        }
        else {
            throw new NoUserFoundException("user with email "+ email+ "doesn't exist in the database");
        }
    }

    public Users findUserWithUsername(String username) throws NoUserFoundException {
        Optional<Users>optionalUsers= repository.findUserByUsername(username);
        if(optionalUsers.isPresent()){
            logger.info("user found,  email: {}", optionalUsers.get().getEmail());
            return optionalUsers.get();
        }
        else {
            throw new NoUserFoundException("user with username "+ username+ "doesn't exist in the database");
        }
    }
}
