package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.exception.DaoException;
import com.techelevator.tebucks.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAccountDaoTests extends BaseDaoTests {

    protected static final Account ACCOUNT_1 = new Account(1,1);
    protected static final Account ACCOUNT_2 = new Account(2,2,500.00);
    protected static final  Account ACCOUNT_3 = new Account(3, 3, 1000.00);

    private JdbcAccountDao sut;


    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);
    }


    @Test (expected = DaoException.class)
    public void createAccount_with_non_existent_userId_throws_exception() {
        sut.createAccount(2000000000);

    }


    @Test (expected = IllegalArgumentException.class)
    public void getAccountByUserId_given_less_than_1_throws_exception() {
        sut.getAccountByUserId(0);
    }


    @Test
    public void getAccountByUserId_given_non_existent_user_id_returns_null() {
        Assert.assertNull(sut.getAccountByUserId(2000000000));
    }


}
