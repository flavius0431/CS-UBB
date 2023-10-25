package org.example.domain;

import java.util.List;

public class Participant  extends Entity<Long> {

    private String nume;
    private int varsta;
    private List<Proba> probe;

    public Participant(String nume, int varsta, List<Proba> probe) {
        this.nume = nume;
        this.varsta = varsta;
        this.probe = probe;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public List<Proba> getProbe() {
        return probe;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                '}';
    }

    public void setProbe(List<Proba> probe) {
        this.probe = probe;
    }
}
