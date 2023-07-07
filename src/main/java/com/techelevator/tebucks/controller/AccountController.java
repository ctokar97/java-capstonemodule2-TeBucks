package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.dao.AccountDao;
import com.techelevator.tebucks.dao.TransferDao;
import com.techelevator.tebucks.model.Account;
import com.techelevator.tebucks.model.Transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {


    private AccountDao accountDao;

    @Autowired
    public AccountController(AccountDao accountDao, TransferDao transferDao) {
        this.accountDao = accountDao;
    }

    @GetMapping(path = "/api/account/balance")
    public Account getAccountBalance(Principal principal){
        String userName = principal.getName();
        return accountDao.getAccountByUserName(userName);
    }

}
