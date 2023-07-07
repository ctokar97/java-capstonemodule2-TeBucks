package com.techelevator.tebucks.service;

import com.techelevator.tebucks.model.Transfer;
import com.techelevator.tebucks.security.model.TxLogDto;
import org.springframework.web.client.RestTemplate;

public class RestLoggerService {
    private static final String API_BASE_URL = "https://te-pgh-api.azurewebsites.net/";
    private final RestTemplate restTemplate = new RestTemplate();

//Do we need a basic logger?
//So we can print date with?

//------------------------------------------------------------------------------------------------
//get a token?




//reportToService
//create TxLogDto txLogDto = new TxLogDto();
//need amount, usernameFrom, usernameTo, description
//need headers, token, set bearerAuth, ContentType, HttpEntity <TxLogDto> entity = new HttpEntity<>(txLogDto)
//Create new TxLogDto returnedTxLogDto = null;
//returnedTxLogDto = restTemplate.postForObject(API_BASE_URL + "/api/TxLog", entity, TxLogDto.class)
//catch exception(s)
}
