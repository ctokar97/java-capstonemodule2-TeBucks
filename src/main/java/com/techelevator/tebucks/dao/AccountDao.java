package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.model.Account;

public interface AccountDao {

        Account createAccount(int userId);

        Account getAccountByUserName(String username);

        Account getAccountByUserId(int userId);

        Account updateAccountBalance(double newBalance, int accountId);

//        boolean updateAccountBalance(double newBalance, int accountId);

}


        //double getBalanceByAccountId(int accountId);
