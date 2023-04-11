package org.example;

import org.example.domain.OficiuPersoana;
import org.example.domain.Participant;
import org.example.domain.Proba;
import org.example.repository.database.OficiuPersoanaDBRepo;
import org.example.repository.database.ParticipantDBRepo;
import org.example.repository.database.ProbaDBRepo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Main {
    public static void main(String[] args)  {
        Properties proberties = new Properties();
        try{
            proberties.load(new FileReader("bd.config.properties"));
        }catch(IOException e){
            System.out.println("cannot find bd.config"+e);
        }
        OficiuPersoanaDBRepo oficiuPersoanaDBRepo = new OficiuPersoanaDBRepo(proberties);
        ProbaDBRepo probaDBRepo = new ProbaDBRepo(proberties);
        ParticipantDBRepo participantDBRepo =new ParticipantDBRepo(proberties,probaDBRepo);
        if(oficiuPersoanaDBRepo.Login("flavius","1234")!=null){
            System.out.println("e corect");
        }
        else {
            System.out.println("e gresit");
        }
        //List<Proba> probe =new ArrayList<>();
        //probe.add(new Proba(12,25,"Programming"));
        //participantDBRepo.save(new Participant("Mihai",23, probe));
        System.out.println("Toti participantii din db");
        for(Participant participant: participantDBRepo.findAll()){
            System.out.println(participant);
        }



    }


}