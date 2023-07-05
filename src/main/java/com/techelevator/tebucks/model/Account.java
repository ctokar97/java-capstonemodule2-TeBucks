package com.techelevator.tebucks.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Account {
    @NotBlank
    private int accountId;
    @NotBlank
    private int userId;
    @Positive
    private double balance;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
