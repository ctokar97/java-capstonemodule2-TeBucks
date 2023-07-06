package com.techelevator.tebucks.dao;

import com.techelevator.tebucks.exception.DaoException;
import com.techelevator.tebucks.model.Transfer;
import com.techelevator.tebucks.security.dao.UserDao;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {


    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    public JdbcTransferDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;

        this.userDao = userDao;
    }


    public Transfer getTransferbyId(int transferId){
    Transfer transfer = null;
     String sql = "SELECT * FROM transfers where transfer_id = ?;";
     try {
         SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
         if (results.next()) {
             transfer = mapRowToTransfer(results);
         }
         } catch (CannotGetJdbcConnectionException e){
             throw new DaoException("Cannot make a connection to database", e);
         }
     return transfer;
    }

    public List<Transfer> getTransferLists(int userId){
        String username = userDao.getUserById(userId).getUsername();
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * from transfers where user_to = ? or user_from = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, username);
        while(results.next()){
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    public int createTransfer(Transfer newTransfer){
       String sql = "INSERT INTO transfers (transfer_id, transfer_type, transfer_status, user_from, user_to, amount)" +
               "values (DEFAULT, ?, ?, ?, ?, ?) RETURNING transfer_id;";
       int transferId;
       try{
           transferId = jdbcTemplate.queryForObject(sql, int.class, newTransfer.getTransferType(), newTransfer.getTransferStatus(), newTransfer.getUserFrom(), newTransfer.getUserTo(), newTransfer.getAmount());
       }catch(DataAccessException | NullPointerException e){
           throw new NullPointerException("Unable to create new transfer");
       }

        return transferId;
    }//call get transferById

    public boolean updateTransfer(Transfer newTransfer){
      String sql = "Update transfers Set transfer_status = ? WHERE transfer_id = ?;";
        return jdbcTemplate.update(sql, newTransfer.getTransferStatus(), newTransfer.getTransferId()) == 1;
    }

    @Override
    public String getTranferStatus(int transferId) {
       String sql = "SELECT transfer_status FROM transfers WHERE transfer_id = ?;";
       try{
           return jdbcTemplate.queryForObject(sql, String.class, transferId);
       }catch (EmptyResultDataAccessException e){
           throw new EmptyResultDataAccessException("Transfer does not exist", 99);
       }

    }

    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferType(rs.getString("transfer_type"));
        transfer.setTransferStatus(rs.getString("transfer_status"));
        transfer.setUserFrom(userDao.findByUsername(rs.getString("user_from")).getId());
        transfer.setUserTo(userDao.findByUsername(rs.getString("user_to")).getId());
        transfer.setAmount(rs.getDouble("amount"));
        return transfer;
    }
}
