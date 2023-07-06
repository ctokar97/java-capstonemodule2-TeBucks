package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.model.Transfer;

import java.util.List;

public interface TransferDao {

Transfer getTransferbyId(int transferId);

List<Transfer> getTransferLists (int userId);
//Added int userId to make easier access to call userDao to get username

int createTransfer (Transfer newTransfer);
//COME BACK!!!!!//make seperate create for request?

boolean updateTransfer(Transfer newTransfer);
//changed to boolean just to show changes

    String getTranferStatus(int transferId);
//incase we need to just get the status
}
