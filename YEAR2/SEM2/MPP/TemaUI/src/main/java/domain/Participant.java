package domain;

import java.util.List;

public class Participant  extends Entity<Long> {

    private String nume;
    private int varsta;

    private String CNP;
    private List<Proba> probe;

    public Participant(String nume, int varsta, String CNP,List<Proba> probe) {
        this.nume = nume;
        this.varsta = varsta;
        this.CNP=CNP;
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

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
    public void setProbe(List<Proba> probe) {
        this.probe = probe;
    }
    @Override
    public String toString() {
        return nume + ' ' +varsta ;
    }
}
