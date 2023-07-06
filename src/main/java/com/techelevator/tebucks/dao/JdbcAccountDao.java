package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.exception.DaoException;
import com.techelevator.tebucks.model.Account;
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
        newAccount.setUserId(userId);
        return newAccount;
    }

    @Override
    public Account getAccountByUserId(int userId) {
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
    public Account updateAccount(int accountId) {
        Account account = null;
        String sql = "SELECT * FROM account WHERE account_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
            if (results.next()){
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }
/** YOOOOOOOOOOOOOOOOOOOOO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    @Override
    public double getBalanceByAccountId(int accountId) {
        Double balance = null;
        String sql = "SELECT balance FROM accounts WHERE account_id = ?;";
        try{
            balance = jdbcTemplate.queryForObject(sql, double.class, accountId);
            if (balance > 0){
                return balance;
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
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


