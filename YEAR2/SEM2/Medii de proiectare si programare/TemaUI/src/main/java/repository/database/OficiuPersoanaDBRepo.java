package repository.database;

import domain.OficiuPersoana;
import repository.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class OficiuPersoanaDBRepo implements IOficiuPersoana{

    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();

    public OficiuPersoanaDBRepo(Properties properties){
        logger.info("Initializing OficiuPersoanaDBRepo with properties:{}",properties);
        dbUtils=new JdbcUtils(properties);
    }
    @Override
    public OficiuPersoana findOne(Long aLong) {
        logger.traceEntry("findone task{}, elem");
        Connection connection = dbUtils.getConnection();
        OficiuPersoana oficiuPersoana = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM OficiuPersoana WHERE id=(?)")) {
            preparedStatement.setLong(1,aLong);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String user =resultSet.getString("user");
                String password = resultSet.getString("password");
                String oficiu = resultSet.getString("oficiu");
                oficiuPersoana= new OficiuPersoana(user, password,oficiu);
                oficiuPersoana.setId(aLong);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit(oficiuPersoana);
        return oficiuPersoana;
    }

    @Override
    public Iterable<OficiuPersoana> findAll() {
        return null;
    }

    @Override
    public OficiuPersoana save(OficiuPersoana entity) {
        return null;
    }

    @Override
    public OficiuPersoana delete(Long aLong) {
        return null;
    }

    @Override
    public OficiuPersoana update(OficiuPersoana entity) {
        return null;
    }

    @Override
    public OficiuPersoana Login(String user, String password) {
        OficiuPersoana oficiuPersoana=null;
        Connection connection =dbUtils.getConnection();
        try(PreparedStatement preparedStatement=connection.prepareStatement("Select * from OficiuPersoana where user=(?) and password=(?)")){
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                Long id = resultSet.getLong("id");
                String user1=resultSet.getString("user");
                String password1=resultSet.getString("password");
                String oficiu=resultSet.getString("oficiu");
                oficiuPersoana=new OficiuPersoana(user1,password1,oficiu);
                oficiuPersoana.setId(id);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit();
        return oficiuPersoana;
    }

}
