package com.techelevator.tebucks.security.dao;

import com.techelevator.tebucks.security.model.RegisterUserDto;
import com.techelevator.tebucks.security.model.User;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    //To have list of users to send or request to
    List<User> allUsersButCurrent(String username);
    //Helper method for above
    User findByUsername (String username);

    User getUserById(int userId);

    List<User> getAllUsers();

}
