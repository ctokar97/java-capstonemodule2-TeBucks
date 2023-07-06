package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.model.Account;
import com.techelevator.tebucks.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    final double STARTING_BALANCE = 1000.00;

    @Override
    public Account makeNewAccount(int accountId, int userId) {
        Account newAccount = null;
        newAccount.setUserId(userId);
        return newAccount;
    }

    @Override
    public double getBalanceByAccountId(int accountId) {
        return 0;
    }

    @Override
    public List<Transfer> getTransfers(int accountId) {
        return null;
    }
}
