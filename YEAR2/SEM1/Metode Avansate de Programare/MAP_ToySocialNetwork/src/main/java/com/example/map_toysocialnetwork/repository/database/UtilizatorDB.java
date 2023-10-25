package com.example.map_toysocialnetwork.repository.database;

import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.validators.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UtilizatorDB extends AbstractDBRepository<Long, Utilizator> {

    public UtilizatorDB(String url, String username, String password, Validator<Utilizator> validator) {
        super(url, username, password, validator);

    }

    @Override
    public Utilizator findOne(Long aLong) {
        Utilizator user = null;

        String sql="SELECT * from users WHERE id = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, aLong);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            Long idUser = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            user = new Utilizator(firstName, lastName,email,password);
            user.setId(idUser);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
  /*//returneaza un utilizator dupa un email
    public Utilizator findOneEmail(String email) {
        Utilizator user = null;

        String sql="SELECT * from users WHERE email = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            Long idUser = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String emailUs = resultSet.getString("email");
            String password = resultSet.getString("password");

            user = new Utilizator(firstName, lastName,emailUs,password);
            user.setId(idUser);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }*/

    @Override
    public Utilizator save(Utilizator entity) {
        String sql = "INSERT INTO users(first_name,last_name,email,password) VALUES(?,?,?,?)";
        try(Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getFirstName());
            preparedStatement.setString(2,entity.getLastName());
            preparedStatement.setString(3,entity.getEmail());
            preparedStatement.setString(4,entity.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utilizator delete(Long aLong) {


        Utilizator user = this.findOne(aLong);
        if(user == null)
            return null;

        String sql = "DELETE FROM users WHERE id = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Utilizator update(Utilizator entity) {
        Utilizator user = this.findOne(entity.getId());
        if(user == null)
            return entity;

        String sql = "UPDATE users SET first_name = (?), last_name = (?)  WHERE id = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Iterable<Utilizator> findAll() {
        Set<Utilizator> users = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Utilizator u = new Utilizator(firstName, lastName,email,password);
                u.setId(id);
                users.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e){
            e.getMessage();
        }

        return users;
    }


}
