package com.techelevator.tebucks.security.dao;

import com.techelevator.tebucks.exception.DaoException;
import com.techelevator.tebucks.security.model.RegisterUserDto;
import com.techelevator.tebucks.security.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT user_id, username, password_hash, first_name, last_name, email FROM users WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }
//get back all users
    @Override
    public List<User> getAllUsers() {
       List<User> users = new ArrayList<>();
       String sql = "SELECT user_id, username, password_hash from users WHERE user_id = ?;";

       SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
       while(results.next()){
           User user = mapRowToUser(results);
           users.add(user);
       }

        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT user_id, username, password_hash, first_name, last_name, email FROM users WHERE username = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        // create user
        String sql = "INSERT INTO users (username, password_hash, first_name, last_name, email) VALUES (?, ?, ?, ?, ?) RETURNING user_id";
        String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());
        try {
            Integer newUserId = jdbcTemplate.queryForObject(sql, int.class,
                    user.getUsername(),
                    passwordHash,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail());

            if (newUserId == null) {
                throw new DaoException("Could not create user");
            }

            return getUserById(newUserId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    //All users Except current to be able to see who to send money to
    @Override
    public List<User> allUsersButCurrent(String username) {
       List<User> users = new ArrayList<>();
       User id = findByUsername(username);
       String sql = "SELECT user_id, username, password_hash FROM users Where user_id != ?;";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
       while (results.next()){
           User user = mapRowToUser(results);
           users.add(user);
       }
        return users;
    }
    //helper method to help get all users but current
    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("User cannot be null");

        String sql = "Select user_id, username, password_hash from users Where username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()){
            return mapRowToUser(rowSet);
        }
        return null;//Throw an exception here?
    }



    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }
}
