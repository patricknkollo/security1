package com.security.example.secu.controllers;

import com.security.example.secu.entities.Customer;
import com.security.example.secu.exceptions.NoUserFoundException;
import com.security.example.secu.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers (){
        return service.findAllCustomers();
    }

    @RequestMapping(path = "/app", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomerByUsername (@RequestParam("username") String username) throws NoUserFoundException {
        return service.findCustomerByUsername(username);
    }

    @RequestMapping(path = "/app/id", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomerById (@RequestParam("customerid") Long id) throws NoUserFoundException {
        return service.findCustomerById(id);
    }
}
