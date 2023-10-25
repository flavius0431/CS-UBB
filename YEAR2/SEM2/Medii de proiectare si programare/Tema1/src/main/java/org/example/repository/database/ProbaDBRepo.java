package org.example.repository.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Proba;
import org.example.repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ProbaDBRepo implements IProba{

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ProbaDBRepo(Properties props){
        logger.info("Initializing ProbaDBRepo with properties:{}",props);
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Proba findOne(Long aLong) {
        logger.traceEntry("findone task{}, elem");
        Connection connection = dbUtils.getConnection();
        Proba proba = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Proba WHERE id=(?)")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int varstamin = resultSet.getInt("varstamin");
                int varstamax = resultSet.getInt("varstamax");
                String proba1 = resultSet.getString("proba");
                proba = new Proba(varstamin, varstamax, proba1);
                proba.setId(aLong);
            }

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit(proba);
        return proba;
    }

    @Override
    public Iterable<Proba> findAll() {
        return null;
    }

    @Override
    public Proba save(Proba entity) {
        return null;
    }

    @Override
    public Proba delete(Long aLong) {
        return null;
    }

    @Override
    public Proba update(Proba entity) {
        return null;
    }
}
