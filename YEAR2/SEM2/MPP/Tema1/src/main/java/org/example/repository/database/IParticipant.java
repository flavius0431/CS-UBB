package org.example.repository.database;

import org.example.domain.Participant;
import org.example.repository.Repository0;

import java.util.List;

public interface IParticipant extends Repository0<Long, Participant> {

    int participantiprobe();

    int participantiCategorie();

    List<Participant> participantlaProba(String proba);

    List<Participant> participantCategorie(int varsta);

}
