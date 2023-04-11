package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository {

    private JdbcUtils dbUtils;


    private static final Logger logger = LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        //to do
        return null;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        //to do
        return null;
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("saving task{},elem");
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("INSERT INTO Masini(manufacture,model,year) values(?,?,?)")) {
            preStmt.setString(1, elem.getManufacturer());
            preStmt.setString(2, elem.getModel());
            preStmt.setInt(3, elem.getYear());
            int result = preStmt.executeUpdate();
            logger.trace("Save {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB" + ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Car elem) {
        //to do
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preStm = con.prepareStatement("select * from Masini")) {
            try (ResultSet result = preStm.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String manufacture = result.getString("manufacture");
                    String model = result.getString("model");
                    int year = result.getInt("year");
                    Car car = new Car(manufacture, model, year);
                    car.setId(id);
                    cars.add(car);
                }
            }

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit(cars);
        return cars;
    }
}
