package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.security.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {
    //    @GetMapping(path = "/api/users")
@RequestMapping(path = "/api/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
    return null;
}
}
