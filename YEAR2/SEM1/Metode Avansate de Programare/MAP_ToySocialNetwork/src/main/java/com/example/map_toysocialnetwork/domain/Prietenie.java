package com.example.map_toysocialnetwork.domain;

import com.example.map_toysocialnetwork.utils.Constants;

import java.time.LocalDateTime;

public class Prietenie extends Entity<Long> {

    private Long ID1;
    private Long ID2;

    private String Status;
    private LocalDateTime Prietenidin;

    public Prietenie(Long ID1, Long ID2, LocalDateTime prietenidin) {
        this.ID1 = ID1;
        this.ID2 = ID2;
        Prietenidin = prietenidin;
        this.Status = "pending";
    }
    public Prietenie(Long ID1, Long ID2, LocalDateTime prietenidin,String Status) {
        this.ID1 = ID1;
        this.ID2 = ID2;
        Prietenidin = prietenidin;
        this.Status = Status;
    }

    public Long getID1(){
        return ID1;
    }

    public Long getID2(){
        return ID2;
    }

    public LocalDateTime getPrietenidin(){
        return Prietenidin;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setID1(Long ID1) {
        this.ID1 = ID1;
    }

    public void setID2(Long ID2) {
        this.ID2 = ID2;
    }

    public void setPrietenidin(LocalDateTime prietenidin) {
        Prietenidin = prietenidin;
    }

    @Override
    public String toString() {
        return "Prietenie{" +
                "ID1=" + ID1 +
                ", ID2=" + ID2 +
                ", Prietenidin=" + Prietenidin.format(Constants.DATE_TIME_FORMATTER) +
                '}';
    }
}
