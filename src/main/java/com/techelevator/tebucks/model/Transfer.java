package com.techelevator.tebucks.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.text.DecimalFormat;

public class Transfer {
    @NotBlank
    private int transferId;
    @NotBlank
    private String transferType;
    @NotBlank
    private String transferStatus;
    @NotBlank
    private int userFrom;
    @NotBlank
    private int userTo;
    @Positive
    private double amount;
    @NotBlank
    private int userId;


    public Transfer(){    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
