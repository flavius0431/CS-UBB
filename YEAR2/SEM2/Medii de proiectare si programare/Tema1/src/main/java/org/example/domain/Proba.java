package org.example.domain;

public class Proba extends Entity<Long>{

    private int varstamin;
    private int varstamax;
    private String proba;

    public Proba(int varstamin, int varstamax, String proba) {
        this.varstamin = varstamin;
        this.varstamax = varstamax;
        this.proba = proba;
    }

    public int getVarstamin() {
        return varstamin;
    }

    public void setVarstamin(int vartamin) {
        this.varstamin = vartamin;
    }

    public int getVarstamax() {
        return varstamax;
    }

    public void setVarstamax(int varstamax) {
        this.varstamax = varstamax;
    }

    public String getProba() {
        return proba;
    }

    public void setProba(String proba) {
        this.proba = proba;
    }
}