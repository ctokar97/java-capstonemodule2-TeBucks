package com.techelevator.tebucks.service;

import org.springframework.web.client.RestTemplate;

public class restLoggerService {
    private static final String API_BASE_URL = "https://te-pgh-api.azurewebsites.net/";
    private final RestTemplate restTemplate = new RestTemplate();
}
