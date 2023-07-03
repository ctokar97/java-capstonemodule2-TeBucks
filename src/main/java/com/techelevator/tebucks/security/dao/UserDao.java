package com.techelevator.tebucks.security.dao;

import com.techelevator.tebucks.security.model.RegisterUserDto;
import com.techelevator.tebucks.security.model.User;

public interface UserDao {

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
