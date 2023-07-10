package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.model.Transfer;

import java.util.List;


public interface TransferDao {

Transfer getTransferbyId(int transferId);

List<Transfer> getTransferLists (int userId);


Transfer createTransfer (Transfer newTransfer);


boolean updateTransfer(Transfer newTransfer);
//changed to boolean just to show changes

    String getTranferStatus(int transferId);
//incase we need to just get the status
}
