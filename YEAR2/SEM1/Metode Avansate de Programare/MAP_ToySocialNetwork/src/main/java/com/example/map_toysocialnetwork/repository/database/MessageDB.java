package com.example.map_toysocialnetwork.repository.database;

import com.example.map_toysocialnetwork.domain.Message;
import com.example.map_toysocialnetwork.domain.Prietenie;
import com.example.map_toysocialnetwork.domain.validators.Validator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MessageDB extends AbstractDBRepository <Long, Message>{

    public MessageDB(String url, String username, String password, Validator<Message> validator) {
        super(url, username, password, validator);
    }



    @Override
    public Message findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Message> findAll() {
        Set<Message> messages = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM messages");
            ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next()) {
                Long senderID = resultSet.getLong("senderid");
                Long reciverID = resultSet.getLong("reciverid");
                String content = resultSet.getString("content");
                LocalDateTime dateSend= resultSet.getTimestamp("datesend").toLocalDateTime();
                Message m= new Message(senderID,reciverID,content,dateSend);
                messages.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public Message save(Message entity) {
        String sql = "INSERT INTO messages(senderid,reciverid,content,datesend) VALUES(?,?,?,?)";
        try(Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,entity.getSenderID());
            preparedStatement.setLong(2,entity.getReceiverID());
            preparedStatement.setString(3,entity.getContent());
            preparedStatement.setTimestamp(4,Timestamp.valueOf(entity.getDateSent()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Message delete(Long aLong) {
        return null;
    }

    @Override
    public Message update(Message entity) {
        return null;
    }


}
