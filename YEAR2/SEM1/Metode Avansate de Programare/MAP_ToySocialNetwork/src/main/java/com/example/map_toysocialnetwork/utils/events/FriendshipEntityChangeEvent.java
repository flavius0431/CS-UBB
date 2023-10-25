package com.example.map_toysocialnetwork.utils.events;

import com.example.map_toysocialnetwork.domain.Message;
import com.example.map_toysocialnetwork.domain.Prietenie;


public class FriendshipEntityChangeEvent implements Event{

    private ChangeEventType type;

    private Prietenie data,oldData;

    private Message datam,olddatam;


    public FriendshipEntityChangeEvent(ChangeEventType type, Prietenie data) {
        this.type = type;
        this.data = data;
    }

    public FriendshipEntityChangeEvent(ChangeEventType type, Prietenie data, Prietenie oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public FriendshipEntityChangeEvent(ChangeEventType type, Message datam) {
        this.type = type;
        this.datam = datam;
    }
    public FriendshipEntityChangeEvent(ChangeEventType type, Message datam, Message oldDatam) {
        this.type = type;
        this.datam = datam;
        this.olddatam = oldDatam;
    }



    public ChangeEventType getType() {
        return type;
    }

    public Prietenie getData() {
        return data;
    }

    public Prietenie getOldData() {
        return oldData;
    }
}
