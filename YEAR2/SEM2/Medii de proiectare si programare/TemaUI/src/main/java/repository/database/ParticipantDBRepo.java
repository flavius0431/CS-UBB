package repository.database;

import domain.Participant;
import domain.Proba;
import repository.JdbcUtils;
import repository.database.IParticipant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.database.ProbaDBRepo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;



public class ParticipantDBRepo implements IParticipant {

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
                    String probeid1 = resultSet.getString("probe");
                    String cnp = resultSet.getString("cnp");
                    String[] probid= probeid1.split(",");
                    List<Proba> probe= new ArrayList<>();
                    for( String i : probid){
                        Proba proba=probaDBRepo.findOne(Long.parseLong(i));
                        System.out.println(proba);
                        probe.add(proba);
                    }
                    Participant participant= new Participant(nume,varsta, cnp, probe);
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
        logger.traceEntry("saving participants{0},elem");
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERt INTO Participant(nume,varsta,probe,cnp) values (?,?,?,?)")){
            preparedStatement.setString(1,entity.getNume());
            preparedStatement.setInt(2,entity.getVarsta());
            String probe="";
            for( Proba proba: entity.getProbe()){
                probe= proba.getId()+",";
            }
            if(probe.length()>2)
                probe=probe.substring(0,probe.length()-1);
            preparedStatement.setString(3,probe);
            preparedStatement.setString(4,entity.getCNP());
            int resultate=preparedStatement.executeUpdate();
            logger.trace("Save {0} instance",resultate);

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
        logger.traceEntry("update participants{0},elem");
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Participant SET probe=(?) where cnp=(?)")){
            String probe="";
            for( Proba proba: entity.getProbe()){
                probe += proba.getId()+",";
                System.out.println(proba);
            }
            if(probe.length()>=2)
                probe=probe.substring(0,probe.length()-1);
            preparedStatement.setString(1,probe);
            preparedStatement.setString(2,entity.getCNP());
            int resultate=preparedStatement.executeUpdate();
            logger.trace("Update {0} instance",resultate);

        } catch (Exception e) {
            logger.error(e);
            System.err.println("Error DB"+ e);
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public int participantiprobe(String proba1) {
        int count=0;
        Iterable<Participant> participants = findAll();
        for(Participant p :participants){
            for(Proba prob: p.getProbe()){
                if(Objects.equals(prob.getProba(), proba1)){
                    count+=1;
                    System.out.println(count);
                }
            }
        }
        return count;
    }

    @Override
    public int participantiCategorie(int m,int M,String probaq) {
        int count=0;
        Iterable<Participant> participants = participantlaProba(probaq);
        for(Participant p:participants){
            if(p.getVarsta()>=m && p.getVarsta()<=M){
                count+=1;
                System.out.println(count);
            }
        }
        return count;
    }

    @Override
    public List<Participant> participantlaProba(String proba) {
        Iterable<Participant> participants = findAll();
        List<Participant> participantiinscrisi=new ArrayList<>();
        for(Participant p :participants){
            for(Proba prob: p.getProbe()){
                System.out.println(p.getProbe());
                if(Objects.equals(prob.getProba(), proba)){
                    participantiinscrisi.add(p);
                }
            }
        }
        return participantiinscrisi;
    }

    @Override
    public List<Participant> participantCategorie(int varstam,int varstaM,String proba) {
        Iterable<Participant> participants =  participantlaProba(proba);
        List<Participant> participantiinscrisi=new ArrayList<>();
        for(Participant p :participants){
                if(p.getVarsta()>=varstam && p.getVarsta()<=varstaM){
                    participantiinscrisi.add(p);
                }
            }
        return participantiinscrisi;
    }

    @Override
    public Participant searchwithCNP(String CNP) {
        Iterable<Participant> participants = findAll();
        for(Participant p :participants){
            if(Objects.equals(p.getCNP(), CNP)){
                return p;
            }
        }
        return null;
    }
}
