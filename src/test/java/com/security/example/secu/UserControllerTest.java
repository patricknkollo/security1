package com.security.example.secu;

import com.security.example.secu.controllers.UserController;
import com.security.example.secu.entities.Users;
import com.security.example.secu.services.UserService;
import enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


//@SpringBootTest  -->  more for integration Tests
@ExtendWith(MockitoExtension.class)  // --> for pure Mocking
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {


    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    private Users user = new Users(1L, "username", "password", "email" , Role.USER.name());

    @Test
    void test_getAllUserFromDB(){
        List<Users> usersList = Arrays.asList(user);
        Mockito.when(service.findAllUser()).thenReturn(usersList);
        List<Users>result = controller.getAllUserFromDB();
        Assertions.assertEquals(usersList, result);

    }
}
