package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.dao.AccountDao;
import com.techelevator.tebucks.dao.TransferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransferDao transferDao;


//    @GetMapping(path = "/api/account/balance")
//    public Account getBalance(){
//        Account n account;
//    }

//    @GetMapping(path = "api/account/transfers")
//    public List<Transfer> getTransfers(Principal principal){
//    }
}
