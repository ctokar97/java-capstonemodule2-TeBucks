package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.dao.AccountDao;
import com.techelevator.tebucks.dao.TransferDao;
import com.techelevator.tebucks.model.NewTransferDto;
import com.techelevator.tebucks.model.Transfer;
import com.techelevator.tebucks.model.TransferStatusUpdateDto;
import com.techelevator.tebucks.security.dao.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransferController {
    private final TransferDao transferDao;
    private final AccountDao accountDao;
    private final UserDao userDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

//eventually need to implement tearsService

    //    @GetMapping(path = "
//
//    @GetMapping(path = "/api/transfers/{id)")
//
//    @PostMapping(path = "api/transfers")
//
//    @PutMapping(path = "/api/transfers/{id}/status")
//    @GetMapping(path = "api/account/transfers")
//    public List<Transfer> getMyTransfers(Principal principal) {
//        List<Transfer> transfers = new ArrayList<>();          //getTransfers(Principal principal){
//        return transfers;
//    }

    @ResponseStatus (HttpStatus.OK)
    @GetMapping(path = "/api/account/transfers")
    public List<Transfer> getAllTransfers(Principal principal){
        return transferDao.getTransferLists(userDao.findByUsername(principal.getName()).getId()); //Getting all transfers by user
    }

    @GetMapping(path = "api/transfers/{id}")
    public Transfer getTransferById(@PathVariable int transferId) {
        return transferDao.getTransferbyId(transferId);//get transfer by id
    }
//sender account balance, transfer amount, sender's ID, transfer username
@ResponseStatus(HttpStatus.CREATED)
@PostMapping(path = "/api/transfers")
public Transfer createTransfer(@RequestBody NewTransferDto transferDto, Principal principal){


    if (transferDto.getUserFrom() == transferDto.getUserTo()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer must be between 2 unique accounts");
    }
    if (transferDto.getAmount() <= 0)
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than 0.");

    if(transferDto.getTransferType().equals("Request")){
        Transfer transfer = new Transfer();

        transfer.setTransferType(transferDto.getTransferType());
        transfer.setUserTo(transferDto.getUserTo());
        transfer.setUserFrom(transferDto.getUserFrom());
        transfer.setAmount(transferDto.getAmount());
        transfer.setTransferStatus("Pending");

        return transferDao.createTransfer(transfer);

    }
    if (transferDto.getTransferType().equals("Send")){

    }

    return null;

}
//in this we need amount, transferType, userFrom, userTo
//check for it is between unique accounts
//check transfer amount is greater than 0
//after this set TransferStatus to pending
//code for if status is approved and compare to service that it does not exceed 1000.00
//if amount is fine create the transfer and update the transfer
//create report for tears if transfer would overdraw account
//return transfer
//else set transferId create transfer and return good transfer


@PutMapping(path = "/api/transfers/{id}/status")
public Transfer updateTransfer(@PathVariable int transferId, @RequestBody TransferStatusUpdateDto transferStatusUpdateDto, Principal principal){
Transfer transfer = transferDao.getTransferbyId(transferId);
//check you are not person requesting or request is going to
//if (principal.getName().equals(transfer.getUserFrom());
//thorw forbidden
//check request if incoming transfer type is a request and incoming transfer status equals rejected

    //else if

    //approve request
    //then check if enough money
    //then get transfer





return null;
    }

//in this we will need to be able to get a transferId and set a transferStatus to that id
//we will need to see if it is approved
//we will need to get balance to see if update is valid and report to log if not
//account for overdrawn account
//return the transfer if valid

}
