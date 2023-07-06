package com.techelevator.tebucks.controller;

import com.techelevator.tebucks.model.Transfer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransferController {


//    @GetMapping(path = "
//
//    @GetMapping(path = "/api/transfers/{id)")
//
//    @PostMapping(path = "api/transfers")
//
//    @PutMapping(path = "/api/transfers/{id}/status")
@GetMapping(path = "api/account/transfers")
public List<Transfer> getMyTransfers(Principal principal){
    List<Transfer> transfers = new ArrayList<>();//getTransfers(Principal principal){
    return transfers;
}
//




}
