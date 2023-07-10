package com.techelevator.tebucks.model;

import javax.validation.constraints.AssertTrue;
import java.text.NumberFormat;

public class NewTransferDto {
    private int userFrom;
    private int userTo;
    private double amount;
    private String transferType;

    public NewTransferDto(){};

    public NewTransferDto(int userFrom, int userTo, int amount, String transferType){
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.userTo = amount;
        this.transferType = transferType;
    }

    @AssertTrue
    private boolean isValidTransferType(){
        if (transferType == null){
            return false;
        }
        if (transferType.equals("Send") || transferType.equals("Request")) {
            return true;
        } else return false;
    }

    public int getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(int userFrom) {
        this.userFrom = userFrom;
    }

    public int getUserTo() {
        return userTo;
    }

    public void setUserTo(int userTo) {
        this.userTo = userTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

}
