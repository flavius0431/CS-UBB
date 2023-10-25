package com.example.practic.repository;

import com.example.practic.domain.City;
import com.example.practic.domain.TrainStation;
import com.example.practic.domain.validators.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CityDBRepo extends AbstractDBRepository<Long, City>{
    public CityDBRepo(String url, String username, String password, Validator<City> validator) {
        super(url, username, password, validator);
    }

    @Override
    public City findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<City> findAll() {
        Set<City> cities = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM city");
            ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                City u = new City(name);
                u.setId(id);
                cities.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e){
            e.getMessage();
        }

        return cities;
    }

    @Override
    public City save(City entity) {
        return null;
    }

    @Override
    public City delete(Long aLong) {
        return null;
    }

    @Override
    public City update(City entity) {
        return null;
    }
}
