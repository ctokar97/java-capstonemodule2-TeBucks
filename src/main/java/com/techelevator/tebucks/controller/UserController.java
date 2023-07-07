package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.dao.AccountDao;
import com.techelevator.tebucks.security.dao.UserDao;
import com.techelevator.tebucks.security.model.RegisterUserDto;
import com.techelevator.tebucks.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserDao userDao;
    private AccountDao accountDao;

    @Autowired
    public UserController(UserDao userDao, AccountDao accountDao){
        this.userDao = userDao;
    }

    @GetMapping(path = "/api/users")
        public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    @PutMapping(path = "/api/users")
    public User addUser(RegisterUserDto registerUserDto){
        accountDao.createAccount(userDao.getUserByUsername(registerUserDto.getUsername()).getId());
    }


}
