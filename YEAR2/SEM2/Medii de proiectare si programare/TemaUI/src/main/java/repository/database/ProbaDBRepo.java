package repository.database;

import domain.Participant;
import domain.Proba;
import repository.JdbcUtils;
import repository.database.IProba;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProbaDBRepo implements IProba {

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ProbaDBRepo(Properties props){
        logger.info("Initializing ProbaDBRepo with properties:{}",props);
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Proba findOne(Long aLong) {
        logger.traceEntry("findone task{0}, elem");
        Connection connection = dbUtils.getConnection();
        Proba proba = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Proba WHERE id=(?)")) {
            preparedStatement.setLong(1,aLong);
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
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        List<Proba> probs = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Proba")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Long id =resultSet.getLong("id");
                    int varstamin = resultSet.getInt("varstamin");
                    int varstamax = resultSet.getInt("varstamax");
                    String proba = resultSet.getString("proba");

                    Proba proba1= new Proba(varstamin,varstamax,proba);

                    proba1.setId(id);
                    probs.add(proba1);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(probs);
        return probs;
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
