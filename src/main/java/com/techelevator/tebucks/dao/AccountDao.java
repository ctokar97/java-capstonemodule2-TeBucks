package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.model.Account;
import com.techelevator.tebucks.model.Transfer;

import java.util.List;

public interface AccountDao {

        Account makeNewAccount(int accountId, int userId);

        double getBalanceByAccountId(int accountId);

        public List<Transfer> getTransfers(int accountId);



}
