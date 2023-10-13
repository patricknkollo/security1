package com.security.example.secu.controllers;

import com.security.example.secu.entities.Users;
import com.security.example.secu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public @ResponseBody List<Users> getAllUserFromDB(){
        return service.findAllUser();
    }
}
