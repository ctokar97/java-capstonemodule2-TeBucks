package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.exception.DaoException;
import com.techelevator.tebucks.model.Account;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account createAccount(int userId) {
        Account newAccount = null;
        String sql = "INSERT INTO accounts (account_id, user_id, balance) VALUES (default, ?, default);";
        try{
            int accountId = jdbcTemplate.queryForObject(sql, int.class, userId);

        }
        newAccount.setUserId(userId);
        return newAccount;
    }

    @Override
    public Account getAccountByUserName(String username) {
        Account account = null;
        String sql = "SELECT * FROM accounts JOIN users USING (?)";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()){
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        if (userId < 1) throw new IllegalArgumentException("Username cannot be null");
        Account account = null;
        String sql = "SELECT * FROM accounts WHERE user_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()){
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }


    @Override
    public Account updateAccount(double newBalance, int accountId) {
        Account account = null;
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newBalance, accountId);
            if (results.next()){
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }

    @Override
    public double getBalanceByAccountId(int accountId) {
        if (accountId < 1) throw new IllegalArgumentException(("Account Id must be valid"));
        double balance = -1.00;
        String sql = "SELECT balance FROM accounts WHERE account_id = ?;";
        try{
            balance = jdbcTemplate.queryForObject(sql, double.class, accountId);
            if (balance >= 0){
                return balance;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        if (balance < 0) throw new DaoException("Account Id must be valid");
        return balance;
    }

    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setUserId(rs.getInt("user_id"));
        account.setBalance(rs.getDouble("balance"));
        return account;
    }
}


