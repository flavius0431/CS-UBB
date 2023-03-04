package com.example.practic.domain;

public class Entity<ID>{

    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}
