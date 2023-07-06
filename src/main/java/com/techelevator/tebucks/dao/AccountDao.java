package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.model.Account;
import com.techelevator.tebucks.model.Transfer;

import java.util.List;

public interface AccountDao {

        Account createAccount(int userId);

        Account getAccountByUserId(int userId);

        Account updateAccount(int accountId);

        double getBalanceByAccountId(int accountId);



}
