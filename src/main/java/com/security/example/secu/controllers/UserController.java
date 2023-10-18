package com.security.example.secu.controllers;

import com.security.example.secu.entities.Users;
import com.security.example.secu.exceptions.NoUserFoundException;
import com.security.example.secu.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@ShellComponent
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @ShellMethod(key = "sec -ls", value = "get the list of all the users in the database")
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public @ResponseBody List<Users> getAllUserFromDB(){
        return service.findAllUser();
    }

    @ShellMethod(key = "sec pull id", value = "get the list of all the users in the database")
    @RequestMapping(path = "/users/userid/id", method = RequestMethod.GET)
    public @ResponseBody Users getUserById(@ShellOption(value = "-i") @RequestParam("id") Long userid) throws NoUserFoundException {
        return service.findUserWithID(userid);
    }

    @ShellMethod(key = "sec pull username", value = "get the list of all the users in the database")
    @RequestMapping(path = "/users/name/nom", method = RequestMethod.GET)
    public @ResponseBody Users getUserByUsername(@ShellOption(value = "-u")@RequestParam("username") String username) throws NoUserFoundException {
        //logger.info("the user is: {}", principal.getName());
        return service.findUserWithUsername(username);
    }

    @ShellMethod(key = "sec pull mail", value = "get the list of all the users in the database")
    @RequestMapping(path = "/users/email/mail", method = RequestMethod.GET)
    public @ResponseBody Users getUserByUserMail(@ShellOption(value = "-e")@RequestParam("email") String mail) throws NoUserFoundException {
        return service.findUserWithEmail(mail);
    }
}
