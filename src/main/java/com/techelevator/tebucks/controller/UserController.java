package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.dao.AccountDao;
import com.techelevator.tebucks.dao.TransferDao;
import com.techelevator.tebucks.model.Account;
import com.techelevator.tebucks.model.Transfer;
import com.techelevator.tebucks.dao.AccountDao;
import com.techelevator.tebucks.security.dao.UserDao;
import com.techelevator.tebucks.security.model.RegisterUserDto;
import com.techelevator.tebucks.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserDao userDao;
    private AccountDao accountDao;
    private final TransferDao transferDao;

    @Autowired
    public UserController(UserDao userDao, AccountDao accountDao, TransferDao transferDao){
        this.userDao = userDao;
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    @GetMapping(path = "/api/users")
        public List<User> getAllUsers(Principal principal){
        List<User> allUsers = userDao.getAllUsers();
        if (allUsers == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users could not be found");
        }
        List<User> users = new ArrayList<>();
        for (User user : allUsers){
            if (!(user.getUsername().equals(principal.getName()))){
                users.add(user);
            }
        }
        return users;
    }//ability to access users except current user so current user can send money
//        return userDao.allUsersButCurrent(principal.getName());
//    }


}
