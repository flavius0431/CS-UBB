package service;

import domain.OficiuPersoana;
import domain.Participant;
import domain.Proba;
import javafx.util.Pair;
import repository.database.*;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private IOficiuPersoana opRepo;
    private IParticipant pRepo;
    private IProba pRepo2;

    public Service(IOficiuPersoana opRepo, IParticipant pRepo, IProba pRepo2) {
        this.opRepo = opRepo;
        this.pRepo = pRepo;
        this.pRepo2 = pRepo2;
    }

    public OficiuPersoana login(String user, String password) {
        return this.opRepo.Login(user, password);
    }

    public Iterable<Participant> getAllParticipants() {
        return this.pRepo.findAll();
    }

    public Participant addParticipant(Participant participant) {
        return this.pRepo.save(participant);
    }

    public Participant updateParticipant(Participant participant) {
        return this.pRepo.update(participant);
    }

    public Proba findProba1(Long id) {
        return this.pRepo2.findOne(id);
    }

    public Iterable<Proba> findAllProbs() {
        return this.pRepo2.findAll();
    }

    public int participantCategorieProba(int m, int M, String p) {
        return this.pRepo.participantiCategorie(m, M, p);
    }

    public List<Participant> ParticipantiProba(String proba) {
        return this.pRepo.participantlaProba(proba);
    }

    public Participant searchwithCNP(String CNP) {
        return this.pRepo.searchwithCNP(CNP);
    }

    public Pair<Integer, Integer> getNrCategorie(Integer varsta) {
        Integer varstamin = 0;
        Integer varstamax = 0;
        if (varsta >= 6 && varsta <= 8) {
            varstamin = 6;
            varstamax = 8;
        }
        if (varsta >= 9 && varsta <= 11) {
            varstamin = 9;
            varstamax = 11;
        }
        if (varsta >= 12 && varsta <= 15) {
            varstamin = 12;
            varstamax = 14;
        }
        return new Pair<>(varstamin, varstamax);
    }

    public Iterable<Participant> findProba(int varstamin, int varstamax, String proba) {
        Iterable<Participant> participants = this.pRepo.findAll();
        List<Participant> probe = new ArrayList<>();
        for (Participant p : participants) {
            if (p.getVarsta() >= varstamin && p.getVarsta() <= varstamax) {
                for (Proba prob : p.getProbe()) {
                    if (prob.getProba().equals(proba)) {
                        probe.add(p);
                    }
                }
            }
        }

        return probe;
    }
}

