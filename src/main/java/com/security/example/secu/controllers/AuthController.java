package com.security.example.secu.controllers;

import com.security.example.secu.entities.AuthResponseDTO;
import com.security.example.secu.entities.LoginDto;
import com.security.example.secu.entities.RegisterDto;
import com.security.example.secu.entities.Users;
import com.security.example.secu.repositories.UserRepository;
import com.security.example.secu.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @ResponseBody
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        Optional<Users> optionalUsers = userRepository.findUserByUsername(registerDto.getUsername());
        if(optionalUsers.isPresent()){
            return new ResponseEntity<>("the username is already taken", HttpStatus.OK);
        }
        Users user = new Users();
        user.setEmail(registerDto.getEmail());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());
        userRepository.save(user);
        return new ResponseEntity<>("registered successfully", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(path = "/login2", method = RequestMethod.POST)
    public ResponseEntity<String> login2(@RequestBody LoginDto loginDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      //  String token = jwtService.generateToken(authentication);
        return new ResponseEntity<>("logged successfully", HttpStatus.OK);
    }
}
