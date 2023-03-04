package com.example.practic.utils.events;

import com.example.practic.domain.City;
import com.example.practic.domain.Entity;

public class EntityChangeEvent implements Event{
    private ChangeEventType type;

    private City data,oldData;


    public EntityChangeEvent(ChangeEventType type, City data)
    {
        this.type=type;
        this.data=data;
    }

    public EntityChangeEvent(ChangeEventType type, City data, City oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

}
