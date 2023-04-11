package org.example.repository.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Participant;
import org.example.domain.Proba;
import org.example.repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;


public class ParticipantDBRepo implements IParticipant{

    private JdbcUtils dbUtils;
    private ProbaDBRepo probaDBRepo;

    private static final Logger logger = LogManager.getLogger();

    public ParticipantDBRepo(Properties props, ProbaDBRepo probaDBRepo){
        logger.info("Initializing ParticipantDBRepo with properties: {}",props);
        dbUtils=new JdbcUtils(props);
        this.probaDBRepo = probaDBRepo;
    }
    @Override
    public Participant findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Participant> findAll() {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        List<Participant> participants = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Participant")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Long id =resultSet.getLong("id");
                    String nume = resultSet.getString("nume");
                    int varsta = resultSet.getInt("varsta");
                    String probeid = resultSet.getString("probe");
                    String[] probid= probeid.split(",");
                    List<Proba> probe= new ArrayList<>();
                    for( String i : probid){
                        Proba proba=probaDBRepo.findOne(Long.parseLong(i));
                        probe.add(proba);
                    }
                    Participant participant= new Participant(nume,varsta,probe);
                    participant.setId(id);
                    participants.add(participant);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(participants);
        return participants;
    }

    @Override
    public Participant save(Participant entity) {
        logger.traceEntry("saving participants{},elem");
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERt INTO Participant(nume,varsta,probe) values (?,?,?)")){
            preparedStatement.setString(1,entity.getNume());
            preparedStatement.setInt(2,entity.getVarsta());
            String probe="";
            for( Proba proba: entity.getProbe()){
                probe= proba.getId()+",";
            }
            if(probe.length()>2)
                probe=probe.substring(0,probe.length()-1);
            preparedStatement.setString(3,probe);
            int resultate=preparedStatement.executeUpdate();
            logger.trace("Save {} instance",resultate);

        } catch (Exception e) {
            logger.error(e);
            System.err.println("Error DB"+ e);
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public Participant delete(Long aLong) {
        return null;
    }

    @Override
    public Participant update(Participant entity) {
        return null;
    }

    @Override
    public int participantiprobe() {
        return 0;
    }

    @Override
    public int participantiCategorie() {
        return 0;
    }

    @Override
    public List<Participant> participantlaProba(String proba) {
        return null;
    }

    @Override
    public List<Participant> participantCategorie(int varsta) {
        return null;
    }
}
