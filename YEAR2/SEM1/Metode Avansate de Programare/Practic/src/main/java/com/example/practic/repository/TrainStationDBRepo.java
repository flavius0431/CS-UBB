package com.example.practic.repository;

import com.example.practic.domain.TrainStation;
import com.example.practic.domain.validators.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TrainStationDBRepo extends AbstractDBRepository<Long, TrainStation> {
    public TrainStationDBRepo(String url, String username, String password, Validator<TrainStation> validator) {
        super(url, username, password, validator);
    }

    @Override
    public TrainStation findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<TrainStation> findAll() {
        Set<TrainStation> trainStations = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM train_station");
            ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next()) {
                Long trainid = resultSet.getLong("trainid");
                Long departureid = resultSet.getLong("departurecityid");
                Long destinationid = resultSet.getLong("destinationcityid");


                TrainStation u = new TrainStation(departureid,destinationid);
                u.setId(trainid);
                trainStations.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e){
            e.getMessage();
        }

        return trainStations;
    }

    @Override
    public TrainStation save(TrainStation entity) {
        return null;
    }

    @Override
    public TrainStation delete(Long aLong) {
        return null;
    }

    @Override
    public TrainStation update(TrainStation entity) {
        return null;
    }
}
