package com.example.map_toysocialnetwork.utils.events;

import com.example.map_toysocialnetwork.domain.Utilizator;

public class UserEntityChangeEvent implements Event{
    private ChangeEventType type;

    private Utilizator data,oldData;


    public UserEntityChangeEvent(ChangeEventType type, Utilizator data)
    {
        this.type=type;
        this.data=data;
    }

    public UserEntityChangeEvent(ChangeEventType type, Utilizator data, Utilizator oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Utilizator getData() {
        return data;
    }

    public Utilizator getOldData() {
        return oldData;
    }
}
