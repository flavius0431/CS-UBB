package repository.database;


import domain.Participant;
import repository.Repository0;

import java.util.List;

public interface IParticipant extends Repository0<Long, Participant> {

    int participantiprobe(String proba);

    int participantiCategorie(int m,int M,String probaq);

    List<Participant> participantlaProba(String proba);

    List<Participant> participantCategorie(int varstam,int varstaM,String proba);

    Participant searchwithCNP(String CNP);

}
