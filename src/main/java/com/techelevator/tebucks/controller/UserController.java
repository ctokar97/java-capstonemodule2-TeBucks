package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.dao.TransferDao;
import com.techelevator.tebucks.model.Transfer;
import com.techelevator.tebucks.security.dao.UserDao;
import com.techelevator.tebucks.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserDao userDao;
    private final TransferDao transferDao;

    @Autowired
    public UserController(UserDao userDao, TransferDao transferDao){
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

    @GetMapping(path = "/api/users")
        public List<User> getAllUsers(Principal principal){ //ability to access users except current user so current user can send money
        return userDao.allUsersButCurrent(principal.getName());
    }

    @ResponseStatus (HttpStatus.OK)
    @GetMapping(path = "/api/account/transfers")
    public List<Transfer> getAllTransfers(Principal principal){
        return transferDao.getTransferLists(userDao.findByUsername(principal.getName()).getId()); //Getting all transfers by user
    }

}
