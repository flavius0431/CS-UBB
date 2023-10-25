package com.example.map_toysocialnetwork.repository.database;
import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.Prietenie;
import com.example.map_toysocialnetwork.domain.validators.Validator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public  class PrietenieRepoDB extends AbstractDBRepository<Long, Prietenie> {

    public PrietenieRepoDB(String url, String username, String password, Validator<Prietenie> validator) {
        super(url, username, password, validator);
    }

    @Override
    public Prietenie findOne(Long aLong) {
        Prietenie prietenie = null;

        String sql="SELECT * from friendships WHERE id = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            Long id = resultSet.getLong("id");
            Long ID1 = resultSet.getLong("idu1");
            Long ID2 = resultSet.getLong("idu2");
            LocalDateTime prieteniDin= resultSet.getTimestamp("friendsfrom").toLocalDateTime();
            String Status = resultSet.getString("status");

            prietenie = new Prietenie(ID1,ID2,prieteniDin,Status);
            prietenie.setId(id);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return prietenie;
    }

    @Override
    public Iterable<Prietenie> findAll() {
        Set<Prietenie> users = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM friendships");
            ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long ID1 = resultSet.getLong("idu1");
                Long ID2 = resultSet.getLong("idu2");
                LocalDateTime prieteniDin= resultSet.getTimestamp("friendsfrom").toLocalDateTime();
                String Status = resultSet.getString("status");
                Prietenie p = new Prietenie(ID1,ID2,prieteniDin,Status);
                p.setId(id);
                users.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Prietenie save(Prietenie entity) {
        String sql = "INSERT INTO friendships(id,idu1,idu2,friendsFrom,status) VALUES(?,?,?,?,?)";
        try(Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,entity.getId());
            preparedStatement.setLong(2,entity.getID1());
            preparedStatement.setLong(3,entity.getID2());
            preparedStatement.setTimestamp(4,Timestamp.valueOf(entity.getPrietenidin()));
            preparedStatement.setString(5, entity.getStatus());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Prietenie delete(Long aLong) {
        Prietenie prietenie = this.findOne(aLong);
        if(prietenie == null)
            return null;
        String sql = "DELETE FROM friendships WHERE id = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return prietenie;
    }

    @Override
    public Prietenie update(Prietenie entity) {
        Prietenie user = this.findOne(entity.getId());
        if(user == null)
            return entity;

        String sql = "UPDATE friendships SET friendsfrom = (?), status=(?) WHERE id=(?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1,Timestamp.valueOf(entity.getPrietenidin()));
            preparedStatement.setString(2, entity.getStatus());
            preparedStatement.setLong(3,entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
